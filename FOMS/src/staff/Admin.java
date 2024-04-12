package staff;

import utils.InputScanner;

import java.util.UUID;

import static authorisation.Authorisation.authoriseAdmin;

public class Admin extends Staff {
    private final AdminActions adminActions;
    InputScanner sc = InputScanner.getInstance();


    public Admin(String staffID, String name, StaffRoles role, char gender, int age){
        super(staffID, name, role, gender, age, null);
        this.adminActions = new AdminActions();
    }
    /**
     * Constructor for admin
     *
     * @param id Record ID
     * @param staffID Admin staffID
     * @param name Name of admin
     * @param role Role
     * @param gender Gender of admin
     * @param age Age of admin
     */
    public Admin(UUID id, String staffID, String name, StaffRoles role, char gender, int age){
        super(id, staffID, name, role, gender, age, null);
        this.adminActions = new AdminActions();
    }

    private Staff getStaffToChange(StaffRoles auth) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // Display all staff
        staffDirectory.displayAllStaff(auth);
        // Get item to update by name
        System.out.println("Enter staff ID: ");
        String staffId = sc.next();
        return staffDirectory.getStaff(staffId);
    }

    // Add menu item
    public void addStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminActions.addStaff(auth);
        }
    }

    // Update menu item
    public void updateStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            Staff staffToUpdate = getStaffToChange(auth);
            adminActions.updateStaff(staffToUpdate, auth);
        }
    }

    // Remove menu item
    public void removeStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            Staff staffToRmv = getStaffToChange(auth);
            adminActions.removeStaff(staffToRmv, auth);
        }
    }

//    protected void assignManager(String staffId, String Branch){
//        adminActions.assignManager(staffId, Branch);
//    }
//
//    protected void promoteStaff(String staffID, String Branch){
//        adminActions.promoteStaff(staffID, Branch);
//    }
//
//    protected void transferStaff(String staffID, String Branch){
//        adminActions.transferStaff(staffID, Branch);
//    }
//
//    protected void addBranch(String Branch){
//        adminActions.addBranch(Branch);
//    }
//
//    protected void closeBranch(String Branch){
//        adminActions.closeBranch(Branch);
//    }
//
//    protected String addPayment(String method){
//        return adminActions.addPayment(method);
//    }
//
//    protected String removePayment(String method){
//        return adminActions.removePayment(method);
//    }
}


