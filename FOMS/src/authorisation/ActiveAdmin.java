package authorisation;

import management.StaffRoles;
import management.Admin;

import static authorisation.ActiveView.displayMenu;
import static utils.ValidateHelper.validateIntRange;

/**
 * Authorised operations for active user with an admin role.
 */
public class ActiveAdmin implements IActiveUser {
    /**
     * The staff record of the active admin staff.
     */
    private Admin activeStaff;

    /**
     * Constructor for ActiveAdmin, initialises the activeStaff to null.
     */
    public ActiveAdmin() { this.activeStaff = null; }

    /**
     * Constructor for ActiveAdmin, updates the active admin if they log in.
     *
     * @param activeStaff The type of activeStaff (Admin).
     */
    public ActiveAdmin(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    /**
     * Gets the active admin staff record.
     *
     * @return Admin staff record.
     */
    public Admin getActiveStaff() {
        return this.activeStaff;
    }

    /**
     * Sets the active admin staff record.
     *
     * @param activeStaff The type of activeStaff (Admin).
     */
    public void setActiveStaff(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    /**
     * Logs out by setting the active staff to null.
     */
    public void logout() {
        setActiveStaff(null);
    }

    /**
     * Displays the permissions the admin is authorised to do.
     */
    public void showOptions() {
        String staffID = this.getActiveStaff().getStaffID();
        StaffRoles role = this.getActiveStaff().getRole();

        displayMenu("1. Staff Management\n2. Branch Management\n3. Payment Management\n4. My Account", staffID, role);
        int categoryChoice = validateIntRange(1, 4);

        switch(categoryChoice){
            case 1: // staff details
                displayMenu("1. Add Staff \n2. Update Staff\n3. Remove Staff\n4. Filter Accounts\n5. Promote Staff\n6. Transfer Staff", staffID, role);
                int staffChoice = validateIntRange(1, 6);
                switch (staffChoice) {
                    case 1:
                        // add staff
                        this.getActiveStaff().addStaff(role);
                        break;
                    case 2:
                        // edit staff
                        this.getActiveStaff().updateStaff(role);
                        break;
                    case 3:
                        // remove staff
                        this.getActiveStaff().removeStaff(role);
                        break;
                    case 4:
                        // filter staff records
                        this.getActiveStaff().filterStaff(role);
                        break;
                    case 5:
                        // promote staff
                        this.getActiveStaff().promoteStaff(role);
                        break;
                    case 6:
                        // transfer staff
                        this.getActiveStaff().transferStaff(role);
                        break;
                }
                break;
            case 2: // Branch details
                displayMenu("1. Add branch\n2. Close Branch", staffID, role);
                int branchChoice = validateIntRange(1, 2);
                switch (branchChoice) {
                    case 1:
                        // add branch
                        this.getActiveStaff().addBranch(role);
                        break;
                    case 2:
                        // close branch
                        this.getActiveStaff().closeBranch(role);
                        break;
                }
                break;
            case 3: // Payment methods
                displayMenu("1. Add Payment Method\n2. Remove Payment Method", staffID, role);
                int paymentChoice = validateIntRange(1, 2);
                switch (paymentChoice) {
                    case 1:
                        // add method
                        this.getActiveStaff().addPayment(role);
                        break;
                    case 2:
                        // remove method
                        this.getActiveStaff().rmvPayment(role);
                        break;
                }
                break;
            case 4: // Account
                displayMenu("1. Change Password\n2. Logout", staffID, role);
                int accChoice = validateIntRange(1, 2);
                switch (accChoice) {
                    case 1:
                        // change password
                        this.getActiveStaff().changePassword();
                        break;
                    case 2:
                        // logout
                        this.logout();
                        break;
                }
                break;
        }
    }
}