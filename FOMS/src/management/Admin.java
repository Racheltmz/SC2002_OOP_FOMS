package management;

import management.actions.*;
import management.filter.StaffFilterOptions;

import java.util.UUID;

import static authorisation.Authorisation.authoriseAdmin;
import static management.StaffView.getFieldToFilter;

/**
 * Admin class represents an administrative user with more exclusive privileges than Manager and Staff.
 * This class extends the Staff class and provides methods for administrative actions to manage
 * Branches, Staff/Managers, and Payments.
 */
public class Admin extends Staff {
    private final IAdminStaffActions adminStaffActions = new AdminStaffActions();
    private final IAdminBranchActions adminBranchActions = new AdminBranchActions();
    private final IAdminPaymentActions adminPaymentActions = new AdminPaymentActions();

    /**
     * Constructs an Admin using Staff constructor
     *
     * @param staffID Admin staffID
     * @param name Name of the Admin
     * @param role StaffRole of the Admin
     * @param gender Gender of the Admin
     * @param age Age of the Admin
     */
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

    /**
     * Adds a new staff member.
     *
     * @param auth the authorization role required (Admin).
     */
    public void addStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminStaffActions.addStaff();
        }
    }

    /**
     * Updates an existing staff member's details.
     *
     * @param auth the authorization role required (Admin).
     */
    public void updateStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminStaffActions.updateStaff();
        }
    }

    /**
     * Removes an existing staff member.
     *
     * @param auth the authorization role required (Admin).
     */
    public void removeStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            adminStaffActions.removeStaff();
        }
    }

    /**
     * Filters staff records.
     *
     * @param auth the authorization role required (Admin).
     */
    public void filterStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            // Get field to filter staff records
            StaffFilterOptions option = getFieldToFilter();
            adminStaffActions.filterStaff(option);
        }
    }

    /**
     * Promotes an existing staff member.
     *
     * @param auth the authorization role required (Admin).
     */
    public void promoteStaff(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminStaffActions.promoteStaff();
        }
    }

    /**
     * Transfers a staff member to another Branch.
     *
     * @param auth the authorization role required (Admin).
     */
    public void transferStaff(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminStaffActions.transferStaff();
        }
    }

    /**
     * Adds a new Branch.
     *
     * @param auth the authorization role required (Admin).
     */
    public void addBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminBranchActions.addBranch();
        }
    }

    /**
     * Closes an existing Branch.
     *
     * @param auth the authorization role required (Admin).
     */
    public void closeBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminBranchActions.closeBranch();
        }
    }

    /**
     * Adds a new payment method.
     *
     * @param auth the authorization role required (Admin).
     */
    public void addPayment(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminPaymentActions.addPayment();
        }
    }

    /**
     * Removes an existing payment method.
     *
     * @param auth the authorization role required (Admin).
     */
    public void rmvPayment(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminPaymentActions.rmvPayment();
        }
    }
}