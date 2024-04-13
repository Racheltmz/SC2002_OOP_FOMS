import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;

import authorisation.*;
import order.*;
import utils.InputScanner;
import management.Company;
import staff.Staff;
import staff.StaffRoles;

import static authentication.Authentication.login;
import static utils.Initialisation.initialiseCompany;
import static utils.InputScanner.getInstance;
import static validation.ValidateDataType.validateOption;

// Main app file
public class FOMSApp {
    public static void main(String[] args) {
        // Initialise scanner
        InputScanner sc = getInstance();

        // Initialise company, order queue
        Company company = initialiseCompany();
        OrderQueue orderQueue = new OrderQueue();

        /* FOMS INTERFACE */
        // Iterate until system receives a valid input
        boolean active = true;
        while (active) {
            System.out.println("==================================");
            System.out.println("|         Welcome to FOMS!       |");
            System.out.println("|         By FDAB Group 3        |");
            System.out.println("==================================");
            System.out.println("Please select option");
            int choice = validateOption("1. Customer\n2. Staff\n3: Quit", 1, 3);

            switch (choice) {
                case 1:
                    /* CUSTOMER INTERFACE */
                    CustomerActions.showOptions(company, orderQueue);
                    break;

                case 2:
                    /* STAFF INTERFACE */
                    // Initialise staff
                    ActiveFactory staffFactory = new ActiveFactoryStaff();
                    ActiveUser activeStaff = staffFactory.initInactive();
                    ActiveFactory managerFactory = new ActiveFactoryManager();
                    ActiveUser activeManager = managerFactory.initInactive();
                    ActiveFactory adminFactory = new ActiveFactoryAdmin();
                    ActiveUser activeAdmin = adminFactory.initInactive();
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
                                        staff = login(company.getStaffDirectory());
                                        // Set active staff
                                        if (staff != null) {
                                            if (staff.getRole() == StaffRoles.STAFF) {
                                                activeStaff = staffFactory.initActive(staff);
                                            } else if (staff.getRole() == StaffRoles.MANAGER) {
                                                activeManager = managerFactory.initActive(staff);
                                            } else if (staff.getRole() == StaffRoles.ADMIN) {
                                                activeAdmin = adminFactory.initActive(staff);
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
                    active = false;
                    break;
            }
        }
        // Close scanner when the program terminates
        sc.close();
    }
}
