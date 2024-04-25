package management;

import management.filter.StaffFilterOptions;

import java.util.ArrayList;

import static utils.ValidateHelper.validateIntRange;

/**
 * Handles operations for displaying staff records.
 */
public class StaffView {

    /**
     * Prints the current staff details in a tabled manner.
     *
     * @param staffDirectory The staffDirectory from which to print the details.
     */
    public static void displayStaffDetails(ArrayList<Staff> staffDirectory) {
        System.out.printf("| %-15s | %-20s | %-10s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Branch", "Role", "Gender", "Age");
        System.out.println("=".repeat(85));
        for (Staff staff : staffDirectory) {
            if (staff.getRole() != StaffRoles.ADMIN) {
                System.out.printf("| %-15s | %-20s | %-10s | %-10s | %-8s | %-5s |\n",
                        staff.getStaffID(), staff.getName(), staff.getBranch().getName(), staff.getRole(), staff.getGender(), staff.getAge());
            }
        }
    }

    /**
     * User is prompted to select the staff filter, then the staff details are
     * printed a tabled manner depending on the chosen filter.
     */
    public static StaffFilterOptions getFieldToFilter() {
        System.out.println("1. Branch");
        System.out.println("2. Role");
        System.out.println("3. Gender");
        System.out.println("4. Age");
        int adminChoice = validateIntRange("Enter the number for the field you want to filter the staff list by: ", 1, 4);
        return StaffFilterOptions.of(adminChoice);
    }
}