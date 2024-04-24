package ui;

import authorisation.*;
import management.Staff;
import management.StaffDirectory;
import management.StaffRoles;
import utils.InputScanner;

import java.util.InputMismatchException;

import static authentication.Authentication.login;
import static utils.ValidateHelper.validateIntRange;

public class StaffActions {
    public static void showOptions() {
        // Initialise scanner and staff directory
        InputScanner sc = InputScanner.getInstance();
        StaffDirectory staffDirectory = StaffDirectory.getInstance();

        // Initialise staff
        ActiveFactory staffFactory = new ActiveFactoryStaff();
        IActiveUser activeStaff = staffFactory.initInactive();
        ActiveFactory managerFactory = new ActiveFactoryManager();
        IActiveUser activeManager = managerFactory.initInactive();
        ActiveFactory adminFactory = new ActiveFactoryAdmin();
        IActiveUser activeAdmin = adminFactory.initInactive();
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
                    System.out.println("Please select option:");
                    staffChoice = validateIntRange("1. Login\n2. Return back\nYour choice: ", 1, 2);
                    System.out.println();
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
    }
}
