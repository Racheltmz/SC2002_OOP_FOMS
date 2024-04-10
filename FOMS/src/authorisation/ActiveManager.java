package authorisation;

import utils.InputScanner;
import staff.Manager;
import staff.StaffRoles;
import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;

import java.util.InputMismatchException;

// Authorised operations for active user with a manager role
public class ActiveManager implements ActiveUser {
    // Attribute
    private Manager activeStaff;

    // Constructor
    public ActiveManager() {
        this.activeStaff = null;
    }
    public ActiveManager(Manager activeStaff) {
        this.activeStaff = activeStaff;
    }

    // Functionalities
    public Manager getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Manager activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void showOptions() throws EmptyListException, MenuItemNotFoundException, ItemNotFoundException{
        try {
            InputScanner sc = InputScanner.getInstance();
            System.out.println("-".repeat(30));
            System.out.printf("| StaffID: %s\n| Role: %s\n", getActiveStaff().getStaffID(),
                    getActiveStaff().getRole());
            System.out.println("-".repeat(30));
            System.out.println("Please select option (3 to quit): ");
            System.out.println("1. Display Staff List\n2. Add Menu Item\n3. Update Menu Item\n4. Remove Menu Item\n5. Display new orders\n6. View details of an order\n7. Set Order as Ready\n8. Change Password\n9. Logout");
            int staffChoice = sc.nextInt();
            sc.nextLine();
            switch (staffChoice) {
                case 1:
                    this.getActiveStaff().displayStaffList(StaffRoles.MANAGER);
                    break;
                case 2:
                    this.getActiveStaff().addMenuItem(StaffRoles.MANAGER);
                    break;
                case 3:
                    this.getActiveStaff().updateMenuItem(StaffRoles.MANAGER);
                    break;
                case 4:
                    this.getActiveStaff().removeMenuItem(StaffRoles.MANAGER);
                    break;
                case 5:
                    this.getActiveStaff().getNewOrders(this.activeStaff.getBranch());
                    break;
                case 6:
                    this.getActiveStaff().getOrderDetails();
                    break;
                case 7:
                    this.getActiveStaff().setOrderReady();
                    break;
                case 8:
                    this.getActiveStaff().changePassword(this.getActiveStaff());
                    break;
                case 9:
                    this.logout();
                    break;
                default:
                    System.out.println("Invalid choice, please re-enter: ");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (MenuItemNotFoundException | ItemNotFoundException e) {
            System.out.println("Menu item not found: " + e.getMessage());
        }
    }
}
