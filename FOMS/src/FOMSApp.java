// Import
import java.io.IOException;
import java.util.Scanner;

import Authentication.Authentication;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;
import Authentication.ActiveUsers.ActiveAdmin;
import Authentication.ActiveUsers.ActiveManager;
import Authentication.ActiveUsers.ActiveStaff;
import Management.Staff.Roles;
import Order.Order;
import Order.OrderQueue;

import static Initialisation.Initialisation.initialiseCompany;

// Main app file
public class FOMSApp {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Initialise company
        Company company = initialiseCompany();

        /* FOMS INTERFACE */
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Customer\n2. Staff");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                /* CUSTOMER INTERFACE */
                break;
            case 2:
                /* STAFF INTERFACE */
                Authentication auth = new Authentication();
                ActiveStaff activeStaff = new ActiveStaff();
                ActiveManager activeManager = new ActiveManager();
                ActiveAdmin activeAdmin = new ActiveAdmin();
                Staff staff;

                // Iterate until user successfully logs in
                int staffChoice = 0;
                System.out.println("\nWelcome to the FOMS Login System.");
                sc.nextLine(); // Consume newline character

                do {
                    // Login
                    if (activeStaff.getActiveStaff() == null
                            && activeManager.getActiveStaff() == null
                            && activeAdmin.getActiveStaff() == null) 
                    {
                        System.out.println("\nPlease select option (2 to quit): ");
                        System.out.println("1. Login");
                        staffChoice = sc.nextInt();
                        sc.nextLine(); // Consume newline character
                        if (staffChoice == 1) {
                            staff = auth.login(company);
                            // Set active staff
                            if (staff != null) 
                            {
                                if (staff.getRole() == Roles.STAFF) {
                                    activeStaff.setActiveStaff(staff);
                                } else if (staff.getRole() == Roles.MANAGER) {
                                    activeManager.setActiveStaff((Manager)staff);
                                } else if (staff.getRole() == Roles.ADMIN) {
                                    activeAdmin.setActiveStaff((Admin)staff);
                                }
                                System.out.println("Please select option (4 to quit): ");
                                System.out.print("1. Display new orders\n2. View details of a particular order\n3. Process order\n");
                                int opt = sc.nextInt();
                                sc.nextLine();
                                OrderQueue oq = new OrderQueue(10);
                                switch (opt)
                                {
                                    case 1:
                                        //TODO: RETRIEVE ORDERQUEUE FROM CUSTOMER INTERFACE WHEN CUSTOMER CLASS IS IMPLEMENTED
                                        //just for example, placeholder below
                                        oq.displayOrders(staff.getBranch());
                                        break;
                                    case 2: //view details based on orderID
                                        System.out.println("Enter orderID: ");
                                        String orderid = sc.nextLine();
                                        for (Order order : oq.orders)
                                        {
                                            if (order.getOrderID() == orderid)
                                            {
                                                System.out.println(Order.getOrderById(orderid));       
                                            }   
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Enter orderID: ");
                                        String order_id = sc.nextLine();
                                        for (Order order : oq.orders)
                                        {
                                            if (order.getOrderID() == order_id)
                                            {
                                                order.processOrder(order_id); 
                                            }   
                                        }
                                        break;
                                }
                            }
                        } 
                        else 
                        {
                            System.exit(0);
                        }
                    } 
                    else 
                    {
                        if (activeStaff.getActiveStaff() != null) 
                        {
                            activeStaff.processMenu(company, auth);
                        } 
                        else if (activeManager.getActiveStaff() != null) 
                        {
                            activeManager.processMenu(company, auth);
                        } 
                        else if (activeAdmin.getActiveStaff() != null) 
                        {
                            activeAdmin.processMenu(company, auth);
                        }
                    }
                } while (staffChoice != -1);
                sc.close();
                break;
            case 3:
                sc.close();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}
