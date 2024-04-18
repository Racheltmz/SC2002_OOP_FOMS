package staff;

import staff.filter.StaffFilterOptions;

import java.util.UUID;

import static authorisation.Authorisation.authoriseAdmin;
import static staff.StaffView.getFieldToFilter;

public class Admin extends Staff {
    private final IAdminStaffActions adminStaffActions = new AdminStaffActions();
    private final IAdminBranchActions adminBranchActions = new AdminBranchActions();

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
    public Admin(UUID id, String staffID, String name, StaffRoles role, char gender, int age, String password){
        super(id, staffID, name, role, gender, age, null, password);
    }

    // Add menu item
    public void addStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminStaffActions.addStaff();
        }
    }

    // Update menu item
    public void updateStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminStaffActions.updateStaff();
        }
    }

    // Remove menu item
    public void removeStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminStaffActions.removeStaff();
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
            StaffFilterOptions option = getFieldToFilter();
            adminStaffActions.filterStaff(option);
        }
    }

    public void promoteStaff(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminStaffActions.promoteStaff();
        }
    }

    public void transferStaff(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminStaffActions.transferStaff();
        }
    }

    public void addBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminBranchActions.addBranch();
        }
    }

    public void updateBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminBranchActions.updateBranch();
        }
    }

    public void closeBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminBranchActions.closeBranch();
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


