package Authorisation.ActiveUsers;

import Management.Company;
import Management.Manager;
import Management.Staff.Roles;
import Order.Order;
import Order.OrderQueue;

import java.util.Objects;
import java.util.Scanner;

import static Authentication.Authentication.changePassword;

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

    public void processMenu(Scanner sc, Company company, OrderQueue queue) {
        System.out.printf("\nStaffID: %s\tRole: %s\n", this.getActiveStaff().getStaffID(), Roles.MANAGER);
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Display Staff List\n2. Display new orders\n3. View details of a particular order\n4. Process order\n5. Change Password\n6. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        String orderID;
        switch (staffChoice) {
            case 1:
                this.getActiveStaff().displayStaffList(sc, company, Roles.MANAGER);
                break;
            case 2:
                // TODO: RETRIEVE ORDERQUEUE FROM CUSTOMER INTERFACE WHEN CUSTOMER CLASS IS IMPLEMENTED
                // just for example, placeholder below
                queue.displayOrders(this.activeStaff.getBranch());
                break;
            case 3: // view details based on orderID
                System.out.println("Enter orderID: ");
                orderID = sc.nextLine();
                for (Order order : queue.getOrders()) {
                    if (Objects.equals(order.getOrderID(), orderID)) {
                        System.out.println(Order.getOrderById(orderID));
                    }
                }
                break;
            case 4:
                System.out.println("Enter orderID: ");
                orderID = sc.nextLine();
                for (Order order : queue.getOrders()) {
                    if (Objects.equals(order.getOrderID(), orderID)) {
                        order.processOrder(orderID);
                    }
                }
                break;
            case 5:
                changePassword(sc, this.getActiveStaff());
                break;
            case 6:
                this.logout();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}