package Authorisation;

import Initialisation.InputScanner;
import Management.Company;
import Management.Staff;
import Order.OrderQueue;

import static Initialisation.InputScanner.getInstance;

// Keep track of the staff that is logged in
public class ActiveStaff implements ActiveUserInterface {
    private Staff activeStaff;

    public Staff getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Staff activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void showOptions(Company company, OrderQueue queue) {
        InputScanner sc = getInstance();
        System.out.println("Please select option (3 to quit): ");
        System.out.println(
                "1. Display new orders\n2. View details of an order\n3. Set Order as Ready\n4. Change Password\n5. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                this.getActiveStaff().getNewOrders(this.activeStaff.getBranch(), queue);
                break;
            case 2:
                this.getActiveStaff().getOrderDetails(queue);
                break;
            case 3:
                this.getActiveStaff().setOrderReady(queue);
                break;
            case 4:
                this.getActiveStaff().changePassword(this.getActiveStaff());
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