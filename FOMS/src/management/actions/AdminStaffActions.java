package management.actions;

import branch.Branch;
import branch.BranchDirectory;
import exceptions.DuplicateEntryException;
import management.Staff;
import management.StaffDirectory;
import management.StaffRoles;
import management.filter.StaffFilterOptions;
import utils.InputScanner;

import java.util.InputMismatchException;

import static management.StaffView.displayStaffDetails;
import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.*;

public class AdminStaffActions implements IAdminStaffActions {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();
    IStaffFilterActions filterActions = new StaffFilterActions();

    public void addStaff() {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            int numExistingStaff = staffDirectory.getNumStaff();
            // Get details of staff
            // TODO: validateString
            System.out.println("Enter name: ");
            String name = toProperCase(sc.nextLine());

            // Get the staff ID. Keep getting user input until they enter a new staff id
            String staffId = null;
            do {
                try {
                    System.out.print("Enter staff ID: ");
                    staffId = sc.next();
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
            System.out.print("Select branch: ");
            Branch branch = branchDirectory.getBranchByUserInput();

            // Add new staff
            staffDirectory.addStaff(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch), numExistingStaff);
            System.out.println("Successfully added staff: " + staffId);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update staff
    public void updateStaff() {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayStaffDetails(staffDirectory.getStaffDirectory());
        Staff staffToUpdate = staffDirectory.getStaff();
        boolean success = false;
        while (!success) {
            int option = validateIntRange("1. Update name\n2. Update staff ID\n3. Update age\n\nSelect option (4 to quit): ", 1, 4);
            switch (option) {
                case 1:
                    System.out.println("Enter new name: ");
                    String name = toProperCase(sc.nextLine());
                    staffToUpdate.setName(name);
                    staffDirectory.updateStaff(staffToUpdate);
                    System.out.println("Successfully updated staff's name to " + staffToUpdate.getName() + ".");
                    break;
                case 2:
                    System.out.println("Enter new staffID: ");
                    String staffID = toProperCase(sc.next());
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

    // Remove staff
    public void removeStaff() {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayStaffDetails(staffDirectory.getStaffDirectory());
        Staff staffToRmv = staffDirectory.getStaff();
        staffDirectory.rmvStaff(staffToRmv);
        System.out.println("Successfully removed staff: " + staffToRmv.getStaffID());
    }

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
                int age = validateIntRange("Enter age: ", 16, 80);
                filterActions.applyAgeFilter(age);
                break;
        }
    }

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

    public boolean assignManager(Branch branch) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // Get number of managers
        int numManagers = staffDirectory.getNumStaffBranchRole(branch, StaffRoles.MANAGER);
        // Check if the manager quota has exceeded
        if (numManagers == branch.getManagerQuota()) {
            System.out.println("Unable to assign staff as a manager. The manager quota for " + branch.getLocation() + " is already met.");
            return false;
        } else {
            System.out.println("Able to assign staff as a manager. The manager quota for " + branch.getLocation() + " has not been met.");
            return true;
        }
    }

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

    public void transferStaff(){
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayStaffDetails(staffDirectory.getStaffDirectory());
        Staff staff = staffDirectory.getStaff();
        Branch branchToTransfer;

        // Repeatedly ask for a valid branch until one is chosen
        do {
            System.out.print("Select a branch to transfer to" );
            branchToTransfer = branchDirectory.getBranchByUserInput();
            if(staff.getBranch().equals(branchToTransfer)){
                System.out.print("Staff member was already in this branch. Choose a different branch.");
            }
        } while (staff.getBranch().equals(branchToTransfer));

        if (staff.getRole() == StaffRoles.STAFF && checkStaffQuota(branchToTransfer) ||
                (staff.getRole() == StaffRoles.MANAGER && assignManager(branchToTransfer))) {
            staff.setBranch(branchToTransfer);
            staffDirectory.updateStaff(staff);
            System.out.print("Staff member with ID " + staff.getStaffID() + " has been transferred to " + branchToTransfer.getLocation() + " successfully");
        }
    }
}