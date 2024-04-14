import java.util.InputMismatchException;

import authorisation.*;
import menu.MenuDirectory;
import order.CustomerActions;
import staff.StaffDirectory;
import utils.InputScanner;
import staff.Staff;
import staff.StaffRoles;
import order.OrderQueue;

import static authentication.Authentication.login;
import static utils.ValidateHelper.validateIntRange;

/**
 * Main App File
 * @author Afreen, Gwen, Priya, Rachel, Sanjana
 */
public class FOMSApp {
    public static void main(String[] args) {
        // Initialise scanner, directories, and order queue
        InputScanner sc = InputScanner.getInstance();
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        MenuDirectory menuDirectory = MenuDirectory.getInstance();
        OrderQueue orderQueue = OrderQueue.getInstance();

        // Iterate until system receives a valid input
        int choice = 0;
        do {
            try {
                System.out.println("==================================");
                System.out.println("|       Welcome to Popeyes!      |");
                System.out.println("==================================");
                System.out.println("Please select option");
                choice = validateIntRange("1. Customer\n2. Staff\n3: Quit", 1, 3);

                switch (choice) {
                    case 1:
                        /* CUSTOMER INTERFACE */
                        CustomerActions.showOptions();
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
                                        // Login
                                        staff = login(staffDirectory);
                                        // Set active staff
                                        if (staff.getRole() == StaffRoles.STAFF) {
                                            activeStaff = staffFactory.initActive(staff);
                                        } else if (staff.getRole() == StaffRoles.MANAGER) {
                                            activeManager = managerFactory.initActive(staff);
                                        } else if (staff.getRole() == StaffRoles.ADMIN) {
                                            activeAdmin = adminFactory.initActive(staff);
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
                                        activeStaff.showOptions();
                                    } else if (activeManager.getActiveStaff() != null) {
                                        activeManager.showOptions();
                                    } else if (activeAdmin.getActiveStaff() != null) {
                                        activeAdmin.showOptions();
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
        // Close scanner when the program terminates
        sc.close();
    }
}