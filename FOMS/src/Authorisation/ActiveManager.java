package Authorisation;

import Initialisation.InputScanner;
import Management.Company;
import Management.Manager;
import Management.Staff.Roles;
import Order.OrderQueue;

import static Initialisation.InputScanner.getInstance;

public class ActiveManager implements ActiveUserInterface {
    private Manager activeStaff;

    public Manager getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Manager activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void showOptions(Company company, OrderQueue queue) {
        InputScanner sc = getInstance();
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Display Staff List\n2. Display new orders\n3. View details of an order\n4. Set Order as Ready\n5. Change Password\n6. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                this.getActiveStaff().displayStaffList(company, Roles.MANAGER);
                break;
            case 2:
                this.getActiveStaff().getNewOrders(this.activeStaff.getBranch(), queue);
                break;
            case 3:
                this.getActiveStaff().getOrderDetails(queue);
                break;
            case 4:
                this.getActiveStaff().setOrderReady(queue);
                break;
            case 5:
                this.getActiveStaff().changePassword(this.getActiveStaff());
                break;
            case 6:
                this.logout();
                break;
            default:
                System.out.println("Invalid choice, please re-enter: ");
                break;
        }
    }
}