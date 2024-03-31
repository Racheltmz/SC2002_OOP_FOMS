// Import
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;

import Authorisation.ActiveAdmin;
import Authorisation.ActiveManager;
import Authorisation.ActiveStaff;
import Initialisation.InputScanner;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;
import Management.Staff.Roles;
import Order.OrderQueue;

import static Authentication.Authentication.login;
import static Initialisation.Initialisation.initialiseCompany;
import static Initialisation.InputScanner.getInstance;
import static Menu.Menu.displayItemsByBranch;

// Main app file
public class FOMSApp {

    public static void main(String[] args) {
        InputScanner sc = getInstance();

        // Initialise company and order queue
        Company company = initialiseCompany();
        OrderQueue orderQueue = new OrderQueue();

        /* FOMS INTERFACE */
        // Iterate until system receives a valid input
        int choice = 0;
        do {
            try {
                System.out.println("==================================");
                System.out.println("|         Welcome to FOMS!       |");
                System.out.println("|         By FDAB Group 3        |");
                System.out.println("==================================");
                System.out.println("Please select option");
                System.out.println("1. Customer\n2. Staff\n3: Quit");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        /* CUSTOMER INTERFACE */
                        try {
                            System.out.println("==================================");
                            System.out.println("|              Menu              |");
                            System.out.println("|        Welcome Customer!       |");
                            System.out.println("==================================");
                            // Ask customer to select branch
                            displayItemsByBranch(company);

                            // Customer Options @gwen (test case 4-8, 18)
                            System.out.println("Please select option");
                            System.out.println("1. Browse Menu\n2. Check Order Status\n3. Return back");
                            // Iterate until customer is done using functionalities
                            int customerChoice = 0;
//                            do {
//                                // Menu Browsing
//
//                                // Check order status
//                            } while (customerChoice != 3);
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("Please enter a valid integer only.\n");
                            sc.nextLine();
                        }
                        break;
                    case 2:
                        /* STAFF INTERFACE */
                        // Initialise variables
                        ActiveStaff activeStaff = new ActiveStaff();
                        ActiveManager activeManager = new ActiveManager();
                        ActiveAdmin activeAdmin = new ActiveAdmin();
                        Staff staff;

                        // Iterate until user successfully logs in
                        int staffChoice = 0;
                        do {
                            // Login
                            if (activeStaff.getActiveStaff() == null
                                    && activeManager.getActiveStaff() == null
                                    && activeAdmin.getActiveStaff() == null) {
                                try {
                                    System.out.println("==================================");
                                    System.out.println("|          Login System          |");
                                    System.out.println("|         Welcome Staff!         |");
                                    System.out.println("==================================");
                                    System.out.println("Please select option");
                                    System.out.println("1. Login\n2. Return back");
                                    staffChoice = sc.nextInt();
                                    sc.nextLine(); // Consume newline character
                                    if (staffChoice == 1) {
                                        try {
                                            staff = login(company);
                                            // Set active staff
                                            if (staff != null) {
                                                if (staff.getRole() == Roles.STAFF) {
                                                    activeStaff.setActiveStaff(staff);
                                                } else if (staff.getRole() == Roles.MANAGER) {
                                                    activeManager.setActiveStaff((Manager) staff);
                                                } else if (staff.getRole() == Roles.ADMIN) {
                                                    activeAdmin.setActiveStaff((Admin) staff);
                                                }
                                            }
                                        } catch (NoSuchAlgorithmException e) {
                                            System.out.println("Unable to find algorithm.");
                                        }
                                    } else if (staffChoice < 1 || staffChoice > 2)
                                        System.out.print("Invalid choice, please re-enter\n");
                                } catch (InputMismatchException inputMismatchException) {
                                    System.out.println("Please enter a valid integer only.\n");
                                    sc.nextLine();
                                }
                            } else {
                                try {
                                    if (activeStaff.getActiveStaff() != null) {
                                        activeStaff.showOptions(company, orderQueue);
                                    } else if (activeManager.getActiveStaff() != null) {
                                        activeManager.showOptions(company, orderQueue);
                                    } else if (activeAdmin.getActiveStaff() != null) {
                                        activeAdmin.showOptions(company, orderQueue);
                                    }
                                } catch (InputMismatchException inputMismatchException) {
                                    System.out.println("Please choose a valid option.\n");
                                    sc.nextLine();
                                }
                            }
                        } while (staffChoice != 2);
                        break;
                    case 3:
                        break;
                    default:
                        System.out.print("Invalid choice, please re-enter\n");
                        break;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer only.\n");
                sc.nextLine();
            }
        } while (choice != 3);
    }
}
