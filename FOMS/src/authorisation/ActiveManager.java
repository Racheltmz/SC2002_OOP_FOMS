package authorisation;

import staff.StaffRoles;
import utils.InputScanner;
import staff.Manager;

import static authorisation.ActiveView.displayMenu;

/**
 * Authorised operations for active user with a manager role
 */
public class ActiveManager implements IActiveUser {
    /**
     * The staff record of the active manager staff.
     */
    private Manager activeStaff;

    /**
     * Constructor for ActiveManager, initialises the activeStaff to null.
     */
    public ActiveManager() {
        this.activeStaff = null;
    }

    /**
     * Constructor for ActiveManager, updates the active manager if they log in.
     */
    public ActiveManager(Manager activeStaff) {
        this.activeStaff = activeStaff;
    }

    /**
     * Gets the active manager staff record.
     *
     * @return Manager staff record.
     */
    public Manager getActiveStaff() {
        return activeStaff;
    }

    /**
     * Sets the active manager staff record.
     */
    public void setActiveStaff(Manager activeStaff) {
        this.activeStaff = activeStaff;
    }

    /**
     * Logouts by setting the active staff to null.
     */
    public void logout() {
        setActiveStaff(null);
    }

    /**
     * Displays the permissions the manager is authorised to do.
     */
    public void showOptions() {
        InputScanner sc = InputScanner.getInstance();

        String staffID = getActiveStaff().getStaffID();
        StaffRoles role = getActiveStaff().getRole();

        displayMenu("1. Display Staff List\n2. Add Menu Item\n3. Update Menu Item\n4. Remove Menu Item\n5. Display new orders\n6. View details of an order\n7. Set Order as Ready\n8. Change Password\n9. Logout", staffID, role);
        int staffChoice = sc.nextInt();
        sc.nextLine();

        switch (staffChoice) {
            case 1:
                this.getActiveStaff().displayStaffList(this.activeStaff.getRole(), this.activeStaff.getBranch());
                break;
            case 2:
                this.getActiveStaff().addMenuItem(this.activeStaff.getRole(), this.activeStaff.getBranch());
                break;
            case 3:
                this.getActiveStaff().updateMenuItem(this.activeStaff.getRole(), this.activeStaff.getBranch());
                break;
            case 4:
                this.getActiveStaff().removeMenuItem(this.activeStaff.getRole(), this.activeStaff.getBranch());
                break;
            case 5:
                this.getActiveStaff().getNewOrders(this.activeStaff.getBranch());
                break;
            case 6:
                this.getActiveStaff().getOrderDetails(this.activeStaff.getBranch());
                break;
            case 7:
                this.getActiveStaff().setOrderReady(this.activeStaff.getBranch());
                break;
            case 8:
                this.getActiveStaff().changePassword();
                break;
            case 9:
                this.logout();
                break;
            default:
                System.out.println("Invalid choice, please re-enter: ");
                break;
        }
    }
}
