package authorisation;

import utils.InputScanner;
import staff.Staff;

import static authorisation.ActiveView.displayMenu;

// Authorised operations for active user with a staff role
public class ActiveStaff implements IActiveUser {
    // Attribute
    private Staff activeStaff;

    // Constructor
    public ActiveStaff() { this.activeStaff = null; }
    
    public ActiveStaff(Staff activeStaff) {
        this.activeStaff = activeStaff;
    }

    // Functionalities
    public Staff getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Staff activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void showOptions() {
        InputScanner sc = InputScanner.getInstance();

        System.out.println("=".repeat(30));
        System.out.printf("| StaffID: %s\n| Role: %s\n", getActiveStaff().getStaffID(),
                getActiveStaff().getRole());
        System.out.println("=".repeat(30));

        displayMenu("1. Display new orders\n2. View details of an order\n3. Set Order as Ready\n4. Change Password\n5. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine();

        switch (staffChoice) {
            case 1:
                this.getActiveStaff().getNewOrders(this.activeStaff.getBranch());
                break;
            case 2:
                this.getActiveStaff().getOrderDetails();
                break;
            case 3:
                this.getActiveStaff().setOrderReady();
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