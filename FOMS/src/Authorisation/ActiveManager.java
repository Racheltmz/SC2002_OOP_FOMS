package Authorisation;

import Initialisation.InputScanner;
import Management.Company;
import Management.Manager;
import Management.StaffRoles;
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
        System.out.println("-".repeat(30));
        System.out.printf("| StaffID: %s\n| Role: %s\n", getActiveStaff().getStaffID(),
                getActiveStaff().getRole());
        System.out.println("-".repeat(30));
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Display Staff List\n2. Add Menu Item\n3. Update Menu Item\n4. Remove Menu Item\n5. Display new orders\n6. View details of an order\n7. Set Order as Ready\n8. Change Password\n9. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                this.getActiveStaff().displayStaffList(company, StaffRoles.MANAGER);
                break;
            case 2:
                this.getActiveStaff().addMenuItem(company, StaffRoles.MANAGER);
                break;
            case 3:
                this.getActiveStaff().updateMenuItem(company, StaffRoles.MANAGER);
                break;
            case 4:
                this.getActiveStaff().removeMenuItem(company, StaffRoles.MANAGER);
                break;
            case 5:
                this.getActiveStaff().getNewOrders(this.activeStaff.getBranch(), queue);
                break;
            case 6:
                this.getActiveStaff().getOrderDetails(queue);
                break;
            case 7:
                this.getActiveStaff().setOrderReady(queue);
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
    }
}