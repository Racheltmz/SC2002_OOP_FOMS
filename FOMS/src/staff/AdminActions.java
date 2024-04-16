package staff;

import static staff.AdminView.displayAllStaff;
import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.validateInt;
import static utils.ValidateHelper.validateIntRange;

import java.util.InputMismatchException;

import branch.Branch;
import branch.BranchDirectory;
import exceptions.DuplicateEntryException;
import staff.filter.*;
import utils.IFilter;
import utils.InputScanner;

// Class containing actions that admin can perform
public class AdminActions {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();

    // Add staff
    protected void addStaff(StaffRoles auth) {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            int numExistingStaff = staffDirectory.getNumStaff();
            // Get details of staff
            System.out.println("Enter name: ");
            String name = toProperCase(sc.next());

            String staffId = null;
            // Keep getting user input until they enter a valid staff id
            do {
                try {
                    System.out.println("Enter staff ID: ");
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

            System.out.println("Enter gender (M/F): ");
            char gender = sc.next().charAt(0);

            int age = validateInt("Enter age: ");
            System.out.println("Select branch: ");
            Branch branch = branchDirectory.getBranchByUserInput();

            // Add new staff
            staffDirectory.addStaff(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch), numExistingStaff);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update staff record.
     *
     * @param auth Need to be an admin to update staff record.
     */
    protected void updateStaff(StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
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
                System.out.println("Invalid value, please enter again.");
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!success);
    }

    /**
     * Remove staff record.
     *
     * @param auth Need to be an admin to remove staff record.
     */
    protected void removeStaff(StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayAllStaff(staffDirectory.getStaffDirectory(), auth);
        Staff staffToRmv = staffDirectory.getStaff();
        staffDirectory.rmvStaff(staffToRmv);
    }

    // TODO: confirm design of filter
    // TODO: AFREEN, VALIDATE USER INPUT (WHETHER ITS ACTL M/F ETC)
    protected void filterStaff(StaffFilterOptions option) {
        switch (option) {
            case BRANCH:
                String branch = branchDirectory.getBranchByUserInput().getName();
                filterByBranch(branch);
                break;
            case ROLE:
                System.out.println("Select role (S/M):\nS: Staff\nM: Manager");
                String roleChoice = sc.next();
                filterByRole(roleChoice);
                break;
            case GENDER:
                System.out.println("Select gender (M/F):");
                String genderChoice = sc.next();
                filterByGender(genderChoice);
                break;
            case AGE:
                System.out.println("Enter age:");
                int ageChoice = sc.nextInt();
                filterByAge(ageChoice);
                break;
        }
    }

    private void filterByBranch(String branch) {
        IFilter staffFilterBranch = new StaffFilterBranch();
        staffFilterBranch.filter(branch);
    }

    private void filterByRole(String role) {
        IFilter staffFilterRole = new StaffFilterRole();
        staffFilterRole.filter(role);
    }

    private void filterByGender(String gender) {
        IFilter staffFilterGender = new StaffFilterGender();
        staffFilterGender.filter(gender);
    }

    // TODO: AFREEN, we should filter by less than/equal/more than a certain age (more informative)
    private void filterByAge(int age) {
        IFilter staffFilterAge = new StaffFilterAge();
        staffFilterAge.filter(String.valueOf(age));
    }

    protected boolean assignManager(Branch branch, StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // Get number of managers
        int numManagers = staffDirectory.getNumManagersByBranch(branch, auth);
        // Check if the manager quota has exceeded
        if (numManagers < branch.getManagerQuota()) {
            System.out.println("Able to assign staff as a manager. The manager quota for " + branch.getLocation() + " has not been met.");
            return true;
        } else {
            System.out.println("Unable to assign staff as a manager. The manager quota for " + branch.getLocation() + " is already met.");
            return false;
        }
    }

    protected void promoteStaff(StaffRoles auth){
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        filterByRole(StaffRoles.STAFF.getAcronym());
        Staff staff = staffDirectory.getStaff();
        StaffRoles currentRole = staff.getRole();
        switch (currentRole) {
            case STAFF:
                if (assignManager(staff.getBranch(), auth)) {
                    staffDirectory.upgradeCredentials(staff, auth);
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

    // TODO: Afreen, ensure they don't transfer to the branch they were originally in
    protected void transferStaff(StaffRoles auth){
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        displayAllStaff(staffDirectory.getStaffDirectory(), auth);
        Staff staff = staffDirectory.getStaff();
        Branch branchToTransfer = branchDirectory.getBranchByUserInput();
        if (staff.getRole() == StaffRoles.STAFF ||
                (staff.getRole() == StaffRoles.MANAGER && assignManager(branchToTransfer, auth))) {
            staff.setBranch(branchToTransfer);
            staffDirectory.updateStaff(staff);
            System.out.println("Staff member with ID " + staff.getStaffID() + " has been transferred to " + branchToTransfer.getLocation() + " successfully");
        }
    }

    protected void addBranch(StaffRoles auth) {
        try {
            // Get branch name
            System.out.print("Enter branch name: ");
            String name = sc.nextLine();
            // Get location
            System.out.print("Enter branch location: ");
            String location = sc.nextLine();
            // Get staff quota
            int quota = validateIntRange("Enter staff quota: ", 1, 15);
            Branch newBranch = new Branch(name.toUpperCase(), toProperCase(location), quota);
            branchDirectory.addBranch(newBranch, auth);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    protected void closeBranch(StaffRoles auth) {
        // Get branch by name
        Branch branchToRmv = branchDirectory.getBranchByUserInput();
        branchDirectory.rmvBranch(branchToRmv, auth);
    }


//    protected String addPayment(String method){
//        PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();
//        Payment payment;
//
//        switch(method){
//            case "Credit/Debit Card":
//                payment = new Payment(method);
//                paymentDirectory.addPaymentMtd(payment, StaffRoles.ADMIN); //since only admins can add payment methods
//                break;
//            case "Paypal":
//                payment = new Payment(method);
//                paymentDirectory.addPaymentMtd(payment, StaffRoles.ADMIN);
//                break;
//            default:
//                throw new IllegalArgumentException("Unsupported payment method: " + method);
//        }
//        return "Payment method added successfully";
//    }
//
//    protected String removePayment(String method){
//        PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();
//        if (paymentDirectory.getPaymentDirectory().isEmpty()) {
//            return "No payment methods available to remove";
//        }
//
//        Payment payment = paymentDirectory.getPaymentMtd(method);
//        if(payment != null){
//            paymentDirectory.rmvPaymentMtd(method, StaffRoles.ADMIN);
//            return "Payment method has been removed successfully";
//        }
//        return "Payment method not found.";
//    }


    // TEMP
    //    // Setter for updating role of staff member
//    public void setRole(String staffID, String branch){
//        String staffIDFromObject = this.getStaffID();
//        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
//            if(this.getBranch().equals(branch)){
//                System.out.println("Role updated successfully.");
//            }
//            else{
//                System.out.println("This staff member does not belong to this branch.");
//            }
//        }
//        else{
//            System.out.println("Staff member not found.");
//        }
//    }
//
//    // setter for updating branch of staff member
//    public void setBranch(String staffID, String branch){
//        String staffIDFromObject = this.getStaffID();
//        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
//            if(this.getBranch().equals(branch)){
//                System.out.println("Branch updated successfully");
//            }
//            else{
//                System.out.println("This staff member does not belong to this branch");
//            }
//        }
//        else{
//            System.out.println("Staff member not found");
//        }
//    }
//
//    // setter for updating staff ID
//    public void setStaffID(String staffID, String newStaffID){
//        String staffIDFromObject = this.getStaffID();
//        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
//            try{
//                Field staffIDField = Staff.class.getDeclaredField("staffID");
//                staffIDField.setAccessible(true);
//                staffIDField.set(this, newStaffID);
//                System.out.println("Staff ID updated successfully");
//            }
//            catch(NoSuchFieldException | IllegalAccessException e){
//                System.out.println("Error updating staff ID: " + e.getMessage());
//            }
//        }
//        else{
//            System.out.println("Staff member not found");
//        }
//    }
}
