package Authorisation;

import java.util.Objects;
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

    public void showOptions(Scanner sc, Company company, OrderQueue queue) {
        System.out.printf("\nStaffID: %s\nRole: %s\n\n", this.getActiveStaff().getStaffID(), Roles.STAFF);
        System.out.println("Please select option (3 to quit): ");
        System.out.println(
                "1. Display new orders\n2. View details of a particular order\n3. Process order\n4. Change Password\n5. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                // TODO: RETRIEVE ORDERQUEUE FROM CUSTOMER INTERFACE WHEN CUSTOMER CLASS IS IMPLEMENTED
                // just for example, placeholder below
                queue.displayOrders(this.activeStaff.getBranch());
                break;
            case 2: // view details based on orderID
                System.out.print("Enter orderID: ");
                String orderid = sc.nextLine();
                for (Order order : queue.getOrders()) {
                    if (Objects.equals(order.getOrderID(), orderid)) {
                        System.out.println(Order.getOrderById(orderid));
                    }
                }
                System.out.println("Order does not exist.");
                break;
            case 3:
                System.out.print("Enter orderID: ");
                String order_id = sc.nextLine();
                for (Order order : queue.getOrders()) {
                    if (Objects.equals(order.getOrderID(), order_id)) {
                        order.processOrder(order_id);
                    }
                }
                System.out.println("Order does not exist.");
                break;
            case 4:
                changePassword(sc, this.getActiveStaff());
                break;
            case 5:
                this.logout();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }

    public void logout() {
        setActiveStaff(null);
    }
}