package authorisation;

import branch.Branch;
import management.StaffRoles;
import utils.InputScanner;
import management.Manager;

import static authorisation.ActiveView.displayMenu;
import static utils.ValidateHelper.validateInt;
import static utils.ValidateHelper.validateIntRange;

/**
 * Authorised operations for active user with a manager role.
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
     *
     *  @param activeStaff The type of activeStaff (Manager).
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
     *
     *  @param activeStaff The type of activeStaff (Manager).
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
        String staffID = this.getActiveStaff().getStaffID();
        StaffRoles role = this.getActiveStaff().getRole();
        Branch branch = this.getActiveStaff().getBranch();

        displayMenu("1. Display Staff\n2. Menu Management\n3. Order Management\n4. My Account", staffID, role);
        int categoryChoice = validateInt();

        switch (categoryChoice) {
            case 1:
                this.getActiveStaff().displayStaffList(role, branch);
                break;
            case 2: // menu item details
                displayMenu("1. Add Menu Item\n2. Update Menu Item\n3. Remove Menu Item", staffID, role);
                int menuChoice = validateIntRange(1, 3);
                switch (menuChoice) {
                    case 1:
                        // add item
                        this.getActiveStaff().addMenuItem(role, branch);
                        break;
                    case 2:
                        // edit item
                        this.getActiveStaff().updateMenuItem(role, branch);
                        break;
                    case 3:
                        // remove item
                        this.getActiveStaff().removeMenuItem(role, branch);
                        break;
                }
                break;
            case 3: // customer order
                displayMenu("1. Get New Orders\n2. Get Details of an Order\n3. Set Order as Ready", staffID, role);
                int orderChoice = validateIntRange(1, 3);
                switch (orderChoice) {
                    case 1:
                        // get new orders
                        this.getActiveStaff().getNewOrders(branch);
                        break;
                    case 2:
                        // get details of an order
                        this.getActiveStaff().getOrderDetails(branch);
                        break;
                    case 3:
                        // set order as ready
                        this.getActiveStaff().setOrderReady(branch);
                        break;
                }
                break;
            case 4: // Account
                displayMenu("1. Change Password\n2. Logout", staffID, role);
                int accChoice = validateIntRange(1,2);
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
            default:
                System.out.println("Invalid choice, please re-enter: ");
                break;
        }
    }
}