package authorisation;

import utils.InputScanner;
import staff.Manager;

// Authorised operations for active user with a manager role
public class ActiveManager implements IActiveUser {
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

    public void showOptions() {
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
                this.getActiveStaff().displayStaffList(this.activeStaff.getRole(), this.activeStaff.getBranch());
                break;
            case 2:
                this.getActiveStaff().addMenuItem(this.activeStaff.getRole());
                break;
            case 3:
                this.getActiveStaff().updateMenuItem(this.activeStaff.getRole());
                break;
            case 4:
                this.getActiveStaff().removeMenuItem(this.activeStaff.getRole());
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
