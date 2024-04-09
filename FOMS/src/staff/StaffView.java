package staff;

import java.util.ArrayList;

// Boundary for staff information
public class StaffView {
    public static void displayStaffByBranch(ArrayList<Staff> staffList, String branch) {
        System.out.printf("Staff Details from %s branch:\n", branch);
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Role", "Gender", "Age");
        System.out.println("-".repeat(70));
        for (Staff staff : staffList)
            staff.getStaffDetails();
    }
}
