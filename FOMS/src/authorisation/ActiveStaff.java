package authorisation;

import utils.InputScanner;
import management.Company;
import staff.Staff;
import order.OrderQueue;

import static utils.InputScanner.getInstance;

import exceptions.EmptyListException;

// TODO: STRUCT: CREATE A VIEW FOR STAFF, MANAGEMENT, ADMIN for the print statements (1 for printing the header (staff and role) then 3 more for the mennu
// Authorised operations for active user with a staff role
public class ActiveStaff implements ActiveUser {
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

    public void showOptions(Company company, OrderQueue queue) throws EmptyListException {
        InputScanner sc = getInstance();
        System.out.println("-".repeat(30));
        System.out.printf("| StaffID: %s\n| Role: %s\n", getActiveStaff().getStaffID(),
                getActiveStaff().getRole());
        System.out.println("-".repeat(30));
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