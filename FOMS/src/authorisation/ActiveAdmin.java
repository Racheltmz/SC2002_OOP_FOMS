package authorisation;

import staff.StaffRoles;
import utils.InputScanner;
import staff.Admin;

import static authorisation.ActiveView.displayMenu;

/**
 * Authorised operations for active user with an admin role
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
     */
    public void setActiveStaff(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    /**
     * Logouts by setting the active staff to null.
     */
    public void logout() {
        setActiveStaff(null);
    }

    /**
     * Displays the permissions the admin is authorised to do.
     */
    public void showOptions() {
        InputScanner sc = InputScanner.getInstance();

        String staffID = getActiveStaff().getStaffID();
        StaffRoles role = getActiveStaff().getRole();

        displayMenu("1. Staff Account Options\n2. Staff Transfer and Promotion\n3. Branch Options\n4. My Account", staffID, role);
        int categoryChoice = sc.nextInt();
        sc.nextLine();

        switch(categoryChoice){
            case 1:
                displayMenu("1. Add Staff \n2. Update Staff\n3. Remove Staff\n4. Filter Accounts", staffID, role);
                int staffChoice = sc.nextInt();
                sc.nextLine();
                switch (staffChoice) {
                    case 1:
                        // add staff
                        this.getActiveStaff().addStaff(this.activeStaff.getRole());
                        break;
                    case 2:
                        // edit staff
                        this.getActiveStaff().updateStaff(this.activeStaff.getRole());
                        break;
                    case 3:
                        // remove staff
                        this.getActiveStaff().removeStaff(this.activeStaff.getRole());
                        break;
                    case 4:
                        // filter staff records
                        this.getActiveStaff().filterStaff(this.activeStaff.getRole());
                        break;
                    default:
                        System.out.print("Invalid choice, please re-enter: ");
                        break; 
                }
                break;
            case 2: // Staff Changes
                displayMenu("1. Promote Staff\n2. Transfer Staff", staffID, role);
                int changeChoice = sc.nextInt();
                sc.nextLine();
                switch (changeChoice) {
                    case 1:
                        // promote staff
                        this.getActiveStaff().promoteStaff(this.activeStaff.getRole());
                        break;
                    case 2:
                        // transfer staff
                        this.getActiveStaff().transferStaff(this.activeStaff.getRole());
                        break;
                    default:
                        System.out.println("Invalid choice, please re-enter: ");
                        break;
                }
                break;
            case 3:
                displayMenu("1. Add branch\n2. Update Branch\n3. Close Branch", staffID, role);
                int branchChoice = sc.nextInt();
                sc.nextLine();
                switch(branchChoice){
                    case 1:
                        // add branch
                        this.getActiveStaff().addBranch(this.activeStaff.getRole());
                        break;
                    case 2:
                        // edit branch
                        this.getActiveStaff().updateBranch(this.activeStaff.getRole());
                        break;
                    case 3:
                        // close branch
                        this.getActiveStaff().closeBranch(this.activeStaff.getRole());
                        break;
                    default:
                        System.out.println("Invalid choice, please re-enter: ");
                        break;
            }
            break;
        case 4: // Account
            displayMenu("1. Change Password\n2. Logout", staffID, role);
            int accChoice = sc.nextInt();
            sc.nextLine();
            switch (accChoice) {
                case 1:
                    // change password
                    this.getActiveStaff().changePassword();
                    break;
                case 2:
                    // logout
                    this.logout();
                    break;
                default:
                    System.out.println("Invalid choice, please re-enter: ");
                    break;
            }
            break;
        case 5: // Quit
            System.out.println("Exiting...");
            break;
        default:
            System.out.println("Invalid choice, please re-enter: ");
            break;
        }
    }
}