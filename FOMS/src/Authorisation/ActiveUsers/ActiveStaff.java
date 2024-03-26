package Authorisation.ActiveUsers;

import java.util.Scanner;

import Management.Company;
import Management.Staff;
import Management.Staff.Roles;
import Order.Order;
import Order.OrderQueue;

import static Authentication.Authentication.changePassword;

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

    public void processMenu(Scanner sc, Company company, OrderQueue queue) {
        System.out.printf("\nStaffID: %s\tRole: %s\n", this.getActiveStaff().getStaffID(), Roles.STAFF);
        System.out.println("Please select option (3 to quit): ");
        System.out.println(
                "1. Change Password\n2. Display new orders\n3. View details of a particular order\n4. Process order\n5. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                changePassword(sc, this.getActiveStaff());
                break;
            case 2:
                // TODO: RETRIEVE ORDERQUEUE FROM CUSTOMER INTERFACE WHEN CUSTOMER CLASS IS IMPLEMENTED
                // just for example, placeholder below
                queue.displayOrders(this.activeStaff.getBranch());
                break;
            case 3: // view details based on orderID
                System.out.println("Enter orderID: ");
                String orderid = sc.nextLine();
                for (Order order : queue.getOrders()) {
                    if (order.getOrderID() == orderid) {
                        System.out.println(Order.getOrderById(orderid));
                    }
                }
                break;
            case 4:
                System.out.println("Enter orderID: ");
                String order_id = sc.nextLine();
                for (Order order : queue.getOrders()) {
                    if (order.getOrderID() == order_id) {
                        order.processOrder(order_id);
                    }
                }
                break;
            case 5:
                this.logout();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}