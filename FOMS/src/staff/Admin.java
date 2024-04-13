package staff;

import java.util.UUID;

import static authorisation.Authorisation.authoriseAdmin;

// TODO: AFREEN use DIP by creating an interface for adminView, adminActions
public class Admin extends Staff {
    private final AdminView adminView = new AdminView();
    private final AdminActions adminActions = new AdminActions();

    public Admin(String staffID, String name, StaffRoles role, char gender, int age){
        super(staffID, name, role, gender, age, null);
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
            adminActions.updateStaff(auth);
        }
    }

    // Remove menu item
    public void removeStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminActions.removeStaff(auth);
        }
    }

    /**
     * Filter staff records
     *
     * @param auth Admin role required
     */

    public void filterStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            // Get field to filter staff records
            StaffFilterOptions option = adminView.getFieldToFilter();
            adminActions.filterStaff(option);
        }
    }

    public void promoteStaff(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminActions.promoteStaff(auth);
        }
    }

    public void transferStaff(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminActions.transferStaff(auth);
        }
    }

    public void addBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminActions.addBranch(auth);
        }
    }

    public void closeBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminActions.closeBranch(auth);
        }
    }
//
//    protected String addPayment(String method){
//        return adminActions.addPayment(method);
//    }
//
//    protected String removePayment(String method){
//        return adminActions.removePayment(method);
//    }
}


