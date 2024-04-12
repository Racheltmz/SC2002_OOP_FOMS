package staff;

import static utils.ValidateHelper.validateDouble;
import static utils.ValidateHelper.validateInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import branch.Branch;
import branch.BranchDirectory;
import menu.Menu;
import menu.MenuDirectory;
import menu.MenuItem;
import payment.Payment;
import payment.PaymentDirectory;
import utils.InputScanner;
import utils.StaffXlsxHelper;

// Class containing actions that admin can perform
public class AdminActions {
    // Map to store staff roles
    private Map<String, StaffRoles> staffRolesMap = new HashMap<>();
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();
    MenuDirectory menuDirectory = MenuDirectory.getInstance();
    StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();

    // Add menu item
    public void addStaff(StaffRoles auth) {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            int numExistingStaff = staffDirectory.getNumStaff();
            // Get details of staff
            System.out.println("Enter name: ");
            String name = sc.next();
            System.out.println("Enter staff ID: ");
            String staffId = sc.next();
            // TODO: validate if its S/M/A
            System.out.println("Enter role (S/M/A):\nS: Staff\nM: Manager\nA: Admin");
            char role = sc.next().charAt(0);
            // TODO: validate if its M or F
            System.out.println("Enter gender (M/F): ");
            char gender = sc.next().charAt(0);
            int age = validateInt("Enter age: ");
            System.out.println("Select branch: ");
            String branchName = branchDirectory.getBranchByUserInput();
            Branch branch = branchDirectory.getBranchByName(branchName);

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
    public void updateStaff(Staff staffToUpdate, StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
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
    public void removeStaff(Staff staffToRmv, StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        staffDirectory.rmvStaff(staffToRmv.getId(), staffToRmv.getStaffID(), auth);
    }

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
//
//    protected void addBranch(String Branch){
//        // Initialise scanner
//        InputScanner sc = getInstance();
//        System.out.print("Enter branch name: ");
//        String branchName = sc.nextLine();
//        System.out.print("Enter staff quota: ");
//        int quota = sc.nextInt();
//        while (!(1 <= quota && quota <= 15)) {
//            System.out.println("Invalid staff quota! Please re-enter.");
//            System.out.print("Enter staff quota: ");
//            quota = sc.nextInt();
//        }
//        System.out.print("Enter branch location: ");
//        String location = sc.next();
//        location += sc.nextLine();
//        Branch branch = new Branch(branchName, location, quota);
//        System.out.printf("New branch created. Its details are:\nName: %s\nStaff quota: %d\nManager Quota: %d\nLocation: %s\n", branchName, quota, branch.getManagerQuota(), location);
//    }
//
//    protected void closeBranch(String Branch){
//        // Initialise scanner
//        InputScanner sc = getInstance();
//        BranchDirectory branchDirectory = BranchDirectory.getInstance();
//        StaffDirectory staffDirectory = StaffDirectory.getInstance();
//
//        // Get branch name and location
//        System.out.println("Enter branch name: ");
//        String branchName = sc.nextLine();
//
//        System.out.println("Enter branch location: ");
//        String branchLocation = sc.next();
//
//        Branch branch = branchDirectory.getBranchByName(branchName);
//
//        if(branch != null && branch.getName().equals(branchName)){
//            if(authoriseAdmin(StaffRoles.ADMIN)){
//                branchDirectory.rmvBranch(branchName, StaffRoles.ADMIN);
//
//                ArrayList<Staff> staffToRemove = staffDirectory.filterBranch(branchName);
//                for(Staff staff : staffToRemove){
//                    staffDirectory.rmvStaff(staff.getStaffID(), StaffRoles.ADMIN);
//                }
//                System.out.println("Branch " + branchName + " at " + branchLocation + " has been closed.");
//            }
//            else{
//                System.out.println("You are not authorised to perform this action");
//            }
//        }
//        else{
//            System.out.println("Branch " + branchName + " at location " + branchLocation + " does not exist");
//        }
//    }
//
//    protected void assignManager(String staffId, String Branch){
//        StaffDirectory staffDirectory = StaffDirectory.getInstance();
//        if(authoriseAdmin(StaffRoles.ADMIN)){
//            int numManagers = staffDirectory.getNumManagers(StaffRoles.ADMIN);
//            int numManagersInBranch = 0;
//            ArrayList<Staff> allStaff = staffDirectory.getStaffDirectory();
//            int numStaff = allStaff.size() - numManagers;
//
//            for(Staff staff : allStaff){
//                if(staff.getRole() == StaffRoles.MANAGER){
//                    numManagersInBranch++;
//                }
//            }
//
//            int quota = 0;
//            if(numStaff >= 9 && numStaff <= 15){
//                quota = 3;
//            }
//            else if(numStaff >= 5 && numStaff <= 8){
//                quota = 2;
//            }
//            else if(numStaff >= 1 && numStaff <= 4){
//                quota = 1;
//            }
//
//            if(numManagersInBranch < quota){
//                System.out.println("Manager is assigned to the Branch: " + Branch);
//            }
//            else{
//                System.out.println("Cannot assign any more Managers. Quota reached for Branch. \n");
//            }
//        }
//        else{
//            System.out.println("You are not authorised to perform this action");
//        }
//    }
//
//    protected void promoteStaff(String staffID, String Branch){
//        StaffDirectory staffDirectory = StaffDirectory.getInstance();
//        Staff staff = staffDirectory.getStaff(staffID);
//
//        if(staff != null){
//            StaffRoles currentRole = staff.getRole();
//
//            switch(currentRole){
//                case STAFF:
//                    staffRolesMap.put(staffID, StaffRoles.MANAGER);
//                    System.out.println("Staff member with ID " + staffID + " has been promoted to Manager");
//                    break;
//                case MANAGER:
//                    System.out.println("Manager cannot be promoted");
//                    break;
//                case ADMIN:
//                    System.out.println("Admin cannot be promoted");
//                    break;
//            }
//        }
//        else{
//            System.out.println("Staff member with ID " + staffID + " not found");
//        }
//    }
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
//
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
}
