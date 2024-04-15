package staff;

import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.ValidateHelper.validateInt;
import static staff.AdminView.displayAllStaff;

import branch.Branch;
import branch.BranchDirectory;
import exceptions.DuplicateEntryException;
import utils.InputScanner;

public class StaffAccountActions {
    private InputScanner sc = InputScanner.getInstance(); 
    private BranchDirectory branchDirectory = BranchDirectory.getInstance(); 
    StaffDirectory staffDirectory = StaffDirectory.getInstance();

    protected void addStaff(StaffRoles auth) {
        try {
            int numExistingStaff = staffDirectory.getNumStaff();
            // Get details of staff
            System.out.print("Enter name: ");
            String name = sc.next();

            String staffId = null;
            // Keep getting user input until they enter a valid staff id
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

            // Validating the gender 
            char gender; 

            do{
                System.out.print("Enter gender (M/F): ");
                gender = sc.next().toUpperCase().charAt(0); 
                if(gender != 'M' && gender != 'F'){
                    System.out.print("Invalid input. Please enter 'M or 'F");
                }
            }while(gender != 'M' && gender != 'F');

            // TODO: AFREEN validate if its M or F (done)
            
            int age = validateInt("Enter age: ");
            System.out.print("Select branch: ");
            Branch branch = branchDirectory.getBranchByUserInput();

            // Add new staff
            staffDirectory.addStaff(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch), numExistingStaff, auth);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update staff
    protected void updateStaff(StaffRoles auth) {
        displayAllStaff(staffDirectory.getStaffDirectory(), auth);
        Staff staffToUpdate = staffDirectory.getStaff();
        boolean success = false;
        do {
            try {
                // Update details
                int age = validateInt("Enter age: ");
                staffToUpdate.setAge(age);
                staffDirectory.updateStaff(staffToUpdate);
                success = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Invalid value, please enter again.");
            } catch (InputMismatchException e) {
                System.out.print("Error: " + e.getMessage());
            }
        } while (!success);
    }

    protected void transferStaff(StaffRoles auth){
        Scanner sc = new Scanner(System.in); 
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayAllStaff(staffDirectory.getStaffDirectory(), auth);
        Staff staff = staffDirectory.getStaff();
        Branch branchToTransfer; 

        // Repeatedly ask for a valid branch until one is chosen 
        do{
            System.out.print("Select a branch to transfer to" );
            branchToTransfer = branchDirectory.getBranchByUserInput(); 
            if(staff.getBranch().equals(branchToTransfer)){
                System.out.print("Staff member was already in this branch. Choose a different branch.");
            }
        }while(staff.getBranch().equals(branchToTransfer)); 

        if (staff.getRole() == StaffRoles.STAFF ||
                (staff.getRole() == StaffRoles.MANAGER && assignManager(branchToTransfer, auth))) {
            staff.setBranch(branchToTransfer);
            staffDirectory.updateStaff(staff);
            System.out.print("Staff member with ID " + staff.getStaffID() + " has been transferred to " + branchToTransfer.getLocation() + " successfully");
        }
    }

    // Remove staff
    protected void removeStaff(StaffRoles auth) {
        displayAllStaff(staffDirectory.getStaffDirectory(), auth);
        Staff staffToRmv = staffDirectory.getStaff();
        staffDirectory.rmvStaff(staffToRmv, auth);
    }

    protected boolean assignManager(Branch branch, StaffRoles auth) {
        // Get number of managers
        int numManagers = staffDirectory.getNumManagersByBranch(branch, auth);
        // Check if the manager quota has exceeded
        if (numManagers < branch.getManagerQuota()) {
            System.out.print("Able to assign staff as a manager. The manager quota for " + branch.getLocation() + " has not been met.");
            return true;
        } else {
            System.out.print("Unable to assign staff as a manager. The manager quota for " + branch.getLocation() + " is already met.");
            return false;
        }
    }
}
