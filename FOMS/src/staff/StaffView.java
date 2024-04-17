package staff;

import java.util.ArrayList;

public class StaffView {
    // TODO: remove static
    public static void displayStaffDetails(ArrayList<Staff> staffDirectory) {
        System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Branch", "Role", "Gender", "Age");
        System.out.println("=".repeat(80));
        for (Staff staff : staffDirectory) {
            if (staff.getRole() != StaffRoles.ADMIN) {
                System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-8s | %-5s |\n",
                        staff.getStaffID(), staff.getName(), staff.getBranch().getName(), staff.getRole(), staff.getGender(), staff.getAge());
            }
        }
    }
}
