// Import
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Authorisation.ActiveAdmin;
import Authorisation.ActiveManager;
import Authorisation.ActiveStaff;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;
import Management.Staff.Roles;
import Order.OrderQueue;

import static Authentication.Authentication.login;
import static Initialisation.Initialisation.initialiseCompany;

// Main app file
public class FOMSApp {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Initialise company and order queue
        Company company = initialiseCompany();
        OrderQueue orderQueue = new OrderQueue();

        /* FOMS INTERFACE */
        System.out.println("==================================");
        System.out.println("|         Welcome to FOMS!       |");
        System.out.println("|         By FDAB Group 3        |");
        System.out.println("==================================");
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Customer\n2. Staff");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                /* CUSTOMER INTERFACE */
                break;
            case 2:
                /* STAFF INTERFACE */
                ActiveStaff activeStaff = new ActiveStaff();
                ActiveManager activeManager = new ActiveManager();
                ActiveAdmin activeAdmin = new ActiveAdmin();
                Staff staff;

                // Iterate until user successfully logs in
                int staffChoice = 0;
                System.out.println("\nFOMS Login System.");
                sc.nextLine(); // Consume newline character

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
                                staff = login(sc, company);
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
                            }
                            catch (NoSuchAlgorithmException e) {
                                System.out.println("Unable to find algorithm.");
                            }
                        }
                    } else {
                        if (activeStaff.getActiveStaff() != null) {
                            activeStaff.showOptions(sc, company, orderQueue);
                        } else if (activeManager.getActiveStaff() != null) {
                            activeManager.showOptions(sc, company, orderQueue);
                        } else if (activeAdmin.getActiveStaff() != null) {
                            activeAdmin.showOptions(sc, company, orderQueue);
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
    }
}
