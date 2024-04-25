package management.actions;

import branch.Branch;
import branch.BranchDirectory;
import exceptions.DuplicateEntryException;
import management.Staff;
import management.StaffDirectory;
import management.StaffRoles;
import management.filter.StaffFilterOptions;

import java.util.InputMismatchException;

import static management.StaffView.displayStaffDetails;
import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.*;

/**
 * The AdminStaffActions class provides methods for Admins to perform administrative actions related to staff members,
 * such as adding, updating, removing, filtering, promoting, and transferring staff members.
 */
public class AdminStaffActions implements IAdminStaffActions {
    BranchDirectory branchDirectory = BranchDirectory.getInstance();
    IStaffFilterActions filterActions = new StaffFilterActions();

    /**
     * Adds a new staff member to the system.
     * Prompts the user to input details of the new staff member.
     * Validates the inputted details and adds the new staff member to the staffDirectory.
     */
    public void addStaff() {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            int numExistingStaff = staffDirectory.getNumStaff();
            // Get details of staff
            String name = validateString("Enter name: ");
            name = toProperCase(name);

            // Get the staff ID. Keep getting user input until they enter a new staff id
            String staffId = null;
            do {
                try {
                    staffId = validateString("Enter staff ID: ");
                    boolean isExisting = staffDirectory.staffExistsByStaffID(staffId);
                    if (isExisting) {
                        staffId = null;
                        throw new DuplicateEntryException("Staff not inserted: Duplicate staff entered.");
                    }
                } catch (DuplicateEntryException e) {
                    System.out.println(e.getMessage());
                }
            } while (staffId == null);

            // Get the gender
            char gender = validateGender().charAt(0);

            // Get the age
            int age = validateIntRange("Enter age: ", 16, 80);

            // Get the branch
            System.out.println("Select branch: ");
            Branch branch = branchDirectory.getBranchByUserInput();

            // Add new staff
            staffDirectory.addStaff(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch), numExistingStaff);
            System.out.println("Successfully added staff: " + staffId);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates an existing staff member in the system.
     * Allows the user to choose which attribute of the staff member to update, out of name/staff/age.
     * Validates the inputted details and updates the staff member in the staffDirectory.
     */
    public void updateStaff() {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayStaffDetails(staffDirectory.getStaffDirectory());
        Staff staffToUpdate = staffDirectory.getStaff();
        boolean success = false;
        while (!success) {
            int option = validateIntRange("1. Update name\n2. Update staff ID\n3. Update age\nSelect option (4 to quit): ", 1, 4);
            System.out.println();
            switch (option) {
                case 1:
                    String name = validateString("Enter new name: ");
                    name = toProperCase(name);
                    staffToUpdate.setName(name);
                    staffDirectory.updateStaff(staffToUpdate);
                    System.out.println("Successfully updated staff's name to " + staffToUpdate.getName() + ".");
                    break;
                case 2:
                    String staffID = validateString("Enter new staffID: ");
                    staffToUpdate.setStaffID(staffID);
                    staffDirectory.updateStaff(staffToUpdate);
                    System.out.println("Successfully updated staff's ID to " + staffToUpdate.getStaffID() + ".");
                    break;
                case 3:
                    int age = validateIntRange("Enter updated age: ", 16, 80);
                    staffToUpdate.setAge(age);
                    staffDirectory.updateStaff(staffToUpdate);
                    System.out.println("Successfully updated staff's age to " + staffToUpdate.getAge() + ".");
                    break;
                case 4:
                    success = true;
                    break;
            }
        }
    }

    /**
     * Removes an existing staff member from the staffDirectory.
     * Allows the user to select which staff member has to be removed, and
     * removes the selected staff member from the staffDirectory.
     */
    public void removeStaff() {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayStaffDetails(staffDirectory.getStaffDirectory());
        Staff staffToRmv = staffDirectory.getStaff();
        staffDirectory.rmvStaff(staffToRmv);
        System.out.println("Successfully removed staff: " + staffToRmv.getStaffID());
    }

    /**
     * Filters staff members based on the specified filtering option.
     * Available options to filter by are branch, role, gender and age.
     *
     * @param option The filtering option to apply.
     */
    public void filterStaff(StaffFilterOptions option) {
        switch (option) {
            case BRANCH:
                String branch = branchDirectory.getBranchByUserInput().getName();
                filterActions.applyBranchFilter(branch);
                break;
            case ROLE:
                String role = validateRole();
                filterActions.applyRoleFilter(role);
                break;
            case GENDER:
                String gender = validateGender();
                filterActions.applyGenderFilter(gender);
                break;
            case AGE:
                int age = validateIntRange("Enter lower bound for age: ", 16, 80);
                filterActions.applyAgeFilter(age);
                break;
        }
    }

    /**
     * Checks if the staffQuota for the given Branch has been exceeded.
     *
     * @param branch The Branch to check the staffQuota for.
     * @return true if the staffQuota has not been exceeded, false otherwise.
     */
    private boolean checkStaffQuota(Branch branch) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // Get number of staff
        int numStaff = staffDirectory.getNumStaffBranchRole(branch, StaffRoles.STAFF);
        // Check if the staff quota has exceeded
        if (numStaff == branch.getStaffQuota()) {
            System.out.println("The staff quota for " + branch.getLocation() + " is already met.");
            return false;
        } else {
            System.out.print("The staff quota for " + branch.getLocation() + " has not been met.");
            return true;
        }
    }

    /**
     * Checks if the managerQuota for the given branch has been exceeded.
     *
     * @param branch The Branch to check the managerQuota for.
     * @return true if the managerQuota has not been exceeded, false otherwise.
     */
    public boolean assignManager(Branch branch) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // Get number of managers
        int numManagers = staffDirectory.getNumStaffBranchRole(branch, StaffRoles.MANAGER);
        // Check if the manager quota has exceeded
        if (numManagers == branch.getManagerQuota()) {
            System.out.println("The manager quota for " + branch.getLocation() + " is already met.");
            return false;
        } else {
            System.out.println("The manager quota for " + branch.getLocation() + " has not been met.");
            return true;
        }
    }

    /**
     * Promotes a Staff member to a Manager.
     * Verifies if the staff being promoted is a Staff member, and promotes them to Manager if so.
     */
    public void promoteStaff(){
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        filterActions.applyRoleFilter(StaffRoles.STAFF.getAcronym());
        Staff staff = staffDirectory.getStaff();
        StaffRoles currentRole = staff.getRole();
        switch (currentRole) {
            case STAFF:
                if (assignManager(staff.getBranch())) {
                    staffDirectory.upgradeCredentials(staff);
                    System.out.println("Staff member with ID " + staff.getStaffID() + " has been promoted to Manager.");
                }
                break;
            case MANAGER:
                System.out.println("Manager cannot be promoted.");
                break;
            case ADMIN:
                System.out.println("Admin cannot be promoted.");
                break;
        }
    }

    /**
     * Transfers a staff member to a different branch.
     * Allows the user to select the staff member and the destination branch.
     * Validates the transfer and updates the staff member's branch in the staffDirectory.
     */
    public void transferStaff(){
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayStaffDetails(staffDirectory.getStaffDirectory());
        Staff staff = staffDirectory.getStaff();
        Branch branchToTransfer;

        // Repeatedly ask for a valid branch until one is chosen
        do {
            System.out.println("Select a branch to transfer to" );
            branchToTransfer = branchDirectory.getBranchByUserInput();
            if(staff.getBranch().equals(branchToTransfer)){
                System.out.print("Staff member was already in this branch. Choose a different branch.");
            }
        } while (staff.getBranch().equals(branchToTransfer));

        if (staff.getRole() == StaffRoles.STAFF && checkStaffQuota(branchToTransfer) ||
                (staff.getRole() == StaffRoles.MANAGER && assignManager(branchToTransfer))) {
            staff.setBranch(branchToTransfer);
            staffDirectory.updateStaff(staff);
            System.out.println("Staff member with ID " + staff.getStaffID() + " has been transferred to " + branchToTransfer.getLocation() + " successfully");
        }
    }
}