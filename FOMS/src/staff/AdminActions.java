package staff;

import static utils.ValidateHelper.validateInt;
import static utils.ValidateHelper.validateIntRange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import branch.Branch;
import branch.BranchDirectory;
import utils.Filter;
import utils.InputScanner;

// Class containing actions that admin can perform
// TODO: convert files to apply DIP for admin, split functions based on functionalities (staff account, staff role, branch, payment)
public class AdminActions {
    // Map to store staff roles
    private Map<String, StaffRoles> staffRolesMap = new HashMap<>();
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();

    // Add menu item
    protected void addStaff(StaffRoles auth) {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            int numExistingStaff = staffDirectory.getNumStaff();
            // Get details of staff
            System.out.println("Enter name: ");
            String name = sc.next();
            System.out.println("Enter staff ID: ");
            String staffId = sc.next();
            // TODO: AFREEN validate if its S/M
            System.out.println("Enter role (S/M):\nS: Staff\nM: Manager");
            char role = sc.next().charAt(0);
            // TODO: AFREEN validate if its M or F
            System.out.println("Enter gender (M/F): ");
            char gender = sc.next().charAt(0);
            int age = validateInt("Enter age: ");
            System.out.println("Select branch: ");
            Branch branch = branchDirectory.getBranchByUserInput();

            // Add the new menu item to the menu
            switch (role) {
                case 'S':
                    staffDirectory.addStaff(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch), numExistingStaff, auth);
                    break;
                case 'M':
                    staffDirectory.addStaff(new Manager(staffId, name, StaffRoles.MANAGER, gender, age, branch), numExistingStaff, auth);
                    break;
                case 'A':
                    staffDirectory.addStaff(new Admin(staffId, name, StaffRoles.ADMIN, gender, age), numExistingStaff, auth);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update menu item
    protected void updateStaff(StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        staffDirectory.displayAllStaff();
        Staff staffToUpdate = staffDirectory.getStaff();
        boolean success = false;
        do {
            try {
                // Update details
                int age = validateInt("Enter age: ");
                staffToUpdate.setAge(age);
                staffDirectory.updateStaff(staffToUpdate, auth);
                success = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid value, please enter again.");
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!success);
    }

    // Remove staff
    protected void removeStaff(StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        staffDirectory.displayAllStaff();
        Staff staffToRmv = staffDirectory.getStaff();
        staffDirectory.rmvStaff(staffToRmv, auth);
    }

    // TODO: confirm design of filter
    // TODO: AFREEN, VALIDATE USER INPUT (WHETHER ITS ACTL M/F ETC)
    // TODO: AFREEN linked to AdminActions file (i need essentially another getBranchByUserInput from branchdirectory) for staff, gender
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
        Filter staffFilterBranch = new StaffFilterBranch();
        staffFilterBranch.filter(branch);
    }

    private void filterByRole(String role) {
        Filter staffFilterRole = new StaffFilterRole();
        staffFilterRole.filter(role);
    }

    private void filterByGender(String gender) {
        Filter staffFilterGender = new StaffFilterGender();
        staffFilterGender.filter(gender);
    }

    // TODO: AFREEN, we should filter by less than/equal/more than a certain age (more informative)
    private void filterByAge(int age) {
        Filter staffFilterAge = new StaffFilterAge();
        staffFilterAge.filter(String.valueOf(age));
    }

    private void checkQuota() {

    }

//    protected void assignManager(){
//        StaffDirectory staffDirectory = StaffDirectory.getInstance();
//        // Display all managers
//        filterByRole(StaffRoles.MANAGER.getAcronym());
//
//        // Get number of managers
//        int numManagers = staffDirectory.getNumManagers(StaffRoles.ADMIN);
//
//        int numManagersInBranch = 0;
//        ArrayList<Staff> allStaff = staffDirectory.getStaffDirectory();
//        int numStaff = allStaff.size() - numManagers;
//
//        for(Staff staff : allStaff){
//            if(staff.getRole() == StaffRoles.MANAGER){
//                numManagersInBranch++;
//            }
//        }
//
//        int quota = 0;
//        if(numStaff >= 9 && numStaff <= 15){
//            quota = 3;
//        }
//        else if(numStaff >= 5 && numStaff <= 8){
//            quota = 2;
//        }
//        else if(numStaff >= 1 && numStaff <= 4){
//            quota = 1;
//        }
//
//        if(numManagersInBranch < quota){
//            System.out.println("Manager is assigned to the Branch: " + Branch);
//        }
//        else{
//            System.out.println("Cannot assign any more Managers. Quota reached for Branch. \n");
//        }
//    }

    protected void promoteStaff(StaffRoles auth){
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // TODO: AFREEN, improve the ui
        System.out.println("Promote Staff");
        filterByRole(StaffRoles.STAFF.getAcronym());
        Staff staff = staffDirectory.getStaff();
        StaffRoles currentRole = staff.getRole();
        switch (currentRole) {
            case STAFF:
                staffDirectory.upgradeCredentials(staff, auth);
                System.out.println("Staff member with ID " + staff.getStaffID() + " has been promoted to Manager.");
                break;
            case MANAGER:
                System.out.println("Manager cannot be promoted.");
                break;
            case ADMIN:
                System.out.println("Admin cannot be promoted.");
                break;
        }
    }
//
//    protected void transferStaff(String staffID, String Branch){
//        StaffDirectory staffDirectory = StaffDirectory.getInstance();
//        BranchDirectory branchDirectory = BranchDirectory.getInstance();
//        Staff staff = staffDirectory.getStaff(staffID);
//
//        if(staff != null){
//            boolean newBranchExists = false;
//            for(Branch branch : branchDirectory.getBranchDirectory()){
//                if(branch.getName().equals(Branch)){
//                    newBranchExists = true;
//                    branch.setName(Branch);
//                    System.out.println("Staff member with ID " + staffID + " has been transferred to " + Branch + " successfully");
//                    break;
//                }
//            }
//            if(!newBranchExists){
//                System.out.println("This branch does not exist");
//            }
//        }
//        else{
//            System.out.println("Staff member with ID " + staffID + " not found");
//        }
//    }

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
            Branch newBranch = new Branch(name, location, quota);
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
