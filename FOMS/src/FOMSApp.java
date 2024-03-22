
// Import
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Authentication.Authentication;
import Authentication.ActiveUsers.*;
import Initialisation.Initialisation;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;
import Management.Staff.Roles;

import static Authentication.Authentication.login;
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
                        } else {
                            System.exit(0);
                        }
                    } else {
                        if (activeStaff.getActiveStaff() != null) {
                            activeStaff.processMenu(company);
                        } else if (activeManager.getActiveStaff() != null) {
                            activeManager.processMenu(company);
                        } else if (activeAdmin.getActiveStaff() != null) {
                            activeAdmin.processMenu(company);
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
