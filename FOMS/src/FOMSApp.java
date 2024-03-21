// Import
import java.io.IOException;
import java.util.Scanner;

import Authentication.Authentication;
import Authentication.ActiveAdmin;
import Authentication.ActiveManager;
import Authentication.ActiveStaff;
import Initialisation.Initialisation;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;
import Management.Staff.Roles;

// Main app file
public class FOMSApp {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Initialise company
        Initialisation init = new Initialisation();
        Company company = init.initialiseCompany();

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
                            && activeAdmin.getActiveStaff() == null) {
                        System.out.println("\nPlease select option (2 to quit): ");
                        System.out.println("1. Login");
                        staffChoice = sc.nextInt();
                        sc.nextLine(); // Consume newline character
                        if (staffChoice == 1) {
                            staff = auth.login(sc, company);
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
                        } else {
                            System.exit(0);
                        }
                    } else {
                        // TODO: EDIT CHOICES AND IMPLEMENTATION (too much repetition)
                        if (activeStaff.getActiveStaff() != null) {
                            System.out.printf("\nStaffID: %s\tRole: %s\n", activeStaff.getActiveStaff().getStaffID(), Roles.STAFF);
                            System.out.println("Please select option (3 to quit): ");
                            System.out.println("1. Change Password\n2. Logout");
                            staffChoice = sc.nextInt();
                            sc.nextLine(); // Consume newline character
                            switch (staffChoice) {
                                case 1:
                                    if (activeStaff.getActiveStaff() != null)
                                        auth.changePassword(sc, activeStaff.getActiveStaff());
                                    else
                                        System.out.println("Please login first.");
                                    break;
                                case 2:
                                    activeStaff.logout();
                                    break;
                                default:
                                    System.out.print("Invalid choice, please re-enter: ");
                                    break;
                            }
                        } else if (activeManager.getActiveStaff() != null) {
                            System.out.printf("\nStaffID: %s\tRole: %s\n", activeManager.getActiveStaff().getStaffID(), Roles.MANAGER);
                            System.out.println("Please select option (3 to quit): ");
                            System.out.println("1. Display Staff List\n2. Change Password\n3. Logout");
                            staffChoice = sc.nextInt();
                            sc.nextLine(); // Consume newline character
                            switch (staffChoice) {
                                case 1:
                                    activeManager.getActiveStaff().displayStaffList(company,
                                            Roles.MANAGER);
                                    break;
                                case 2:
                                    if (activeManager.getActiveStaff() != null)
                                        auth.changePassword(sc, activeManager.getActiveStaff());
                                    else
                                        System.out.println("Please login first.");
                                    break;
                                case 3:
                                    activeManager.logout();
                                    break;
                                default:
                                    System.out.print("Invalid choice, please re-enter: ");
                                    break;
                            }

                        } else if (activeAdmin.getActiveStaff() != null) {
                            System.out.printf("\nStaffID: %s\tRole: %s\n", activeAdmin.getActiveStaff().getStaffID(), Roles.ADMIN);
                            System.out.println("Please select option (3 to quit): ");
                            System.out.println("1. Change Password\n2. Logout");
                            staffChoice = sc.nextInt();
                            sc.nextLine(); // Consume newline character
                            switch (staffChoice) {
                                case 1:
                                    if (activeAdmin.getActiveStaff() != null)
                                        auth.changePassword(sc, activeAdmin.getActiveStaff());
                                    else
                                        System.out.println("Please login first.");
                                    break;
                                case 2:
                                    activeAdmin.logout();
                                    break;
                                default:
                                    System.out.print("Invalid choice, please re-enter: ");
                                    break;
                            }
                        }
                    }
                } while (staffChoice != -1);
                break;
            case 3:
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}
