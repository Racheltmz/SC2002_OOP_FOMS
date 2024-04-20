package management;

import management.actions.*;
import management.filter.StaffFilterOptions;

import java.util.UUID;

import static authorisation.Authorisation.authoriseAdmin;
import static management.StaffView.getFieldToFilter;

public class Admin extends Staff {
    private final IAdminStaffActions adminStaffActions = new AdminStaffActions();
    private final IAdminBranchActions adminBranchActions = new AdminBranchActions();
    private final IAdminPaymentActions adminPaymentActions = new AdminPaymentActions();

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

    public void closeBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminBranchActions.closeBranch();
        }
    }

    public void addPayment(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminPaymentActions.addPayment();
        }
    }

    public void rmvPayment(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminPaymentActions.rmvPayment();
        }
    }
}