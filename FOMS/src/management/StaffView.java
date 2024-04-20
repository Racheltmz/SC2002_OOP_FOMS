package management;

import management.filter.StaffFilterOptions;
import utils.InputScanner;

import java.util.ArrayList;

public class StaffView {
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

    public static StaffFilterOptions getFieldToFilter() {
        InputScanner sc = InputScanner.getInstance();
        System.out.println("Enter the number for the field you want to filter the staff list by: ");
        System.out.println("1. Branch");
        System.out.println("2. Role");
        System.out.println("3. Gender");
        System.out.println("4. Age");
        int adminChoice = sc.nextInt();
        return StaffFilterOptions.of(adminChoice);
    }
}