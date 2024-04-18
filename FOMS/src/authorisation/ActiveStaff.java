package authorisation;

import staff.StaffRoles;
import utils.InputScanner;
import staff.Staff;

import static authorisation.ActiveView.displayMenu;

/**
 * Authorised operations for active user with a staff role
 */
public class ActiveStaff implements IActiveUser {
    /**
     * The staff record of the active staff.
     */
    private Staff activeStaff;

    /**
     * Constructor for ActiveStaff, initialises the activeStaff to null.
     */
    public ActiveStaff() { this.activeStaff = null; }

    /**
     * Constructor for ActiveStaff, updates the active staff if they log in.
     */
    public ActiveStaff(Staff activeStaff) {
        this.activeStaff = activeStaff;
    }

    /**
     * Gets the active staff record.
     *
     * @return Staff record.
     */
    public Staff getActiveStaff() {
        return activeStaff;
    }

    /**
     * Sets the active staff record.
     */
    public void setActiveStaff(Staff activeStaff) {
        this.activeStaff = activeStaff;
    }

    /**
     * Logouts by setting the active staff to null.
     */
    public void logout() {
        setActiveStaff(null);
    }

    /**
     * Displays the permissions the staff is authorised to do.
     */
    public void showOptions() {
        InputScanner sc = InputScanner.getInstance();

        String staffID = getActiveStaff().getStaffID();
        StaffRoles role = getActiveStaff().getRole();

        displayMenu("1. Display new orders\n2. View details of an order\n3. Set Order as Ready\n4. Change Password\n5. Logout", staffID, role);
        int staffChoice = sc.nextInt();
        sc.nextLine();

        switch (staffChoice) {
            case 1:
                this.getActiveStaff().getNewOrders(this.activeStaff.getBranch());
                break;
            case 2:
                this.getActiveStaff().getOrderDetails(this.activeStaff.getBranch());
                break;
            case 3:
                this.getActiveStaff().setOrderReady(this.activeStaff.getBranch());
                break;
            case 4:
                this.getActiveStaff().changePassword();
                break;
            case 5:
                this.logout();
                break;
            default:
                System.out.println("Invalid choice, please re-enter: ");
                break;
        }
    }
}