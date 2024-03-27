
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

// Main app file
public class FOMSApp {

    public static void main(String[] args) {
        InputScanner sc = getInstance();

        // Initialise company and order queue
        Company company = initialiseCompany();
        OrderQueue orderQueue = new OrderQueue();

        /* FOMS INTERFACE */
        System.out.println("==================================");
        System.out.println("|         Welcome to FOMS!       |");
        System.out.println("|         By FDAB Group 3        |");
        System.out.println("==================================");

        // Iterate until system receives a valid input
        int choice = 0;
        do {
            try {
                System.out.println("Please select option (3 to quit): ");
                System.out.println("1. Customer\n2. Staff");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        /* CUSTOMER INTERFACE */
                        System.out.println("==================================");
                        System.out.println("=              Menu              =");
                        System.out.println("=        Welcome Customer!       =");
                        System.out.println("==================================");
                        break;
                    case 2:
                        /* STAFF INTERFACE */
                        System.out.println("==================================");
                        System.out.println("=          Login System          =");
                        System.out.println("=         Welcome Staff!         =");
                        System.out.println("==================================");

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
                                System.out.println("\nPlease select option (2 to quit): ");
                                System.out.println("1. Login");
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
                                }
                            } else {
                                System.out.println("==================================");
                                System.out.printf("StaffID: %s\nRole: %s\n", activeStaff.getActiveStaff().getStaffID(),
                                        activeStaff.getActiveStaff().getRole());
                                System.out.println("==================================");
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
                        System.out.print("Invalid choice, please re-enter: ");
                        break;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer only.\n");
                sc.nextLine();
            }
        } while (choice < 1 || choice > 3);

    }
}
