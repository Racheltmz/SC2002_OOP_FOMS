package Management;

import java.util.ArrayList;

public class EmployeeView {
    public static void displayStaffByBranch(ArrayList<Staff> staffList, String branch) {
        System.out.printf("Staff Details from %s branch:\n", branch);
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Role", "Gender", "Age");
        System.out.println("-".repeat(70));
        for (Staff staff : staffList)
            staff.getStaffDetails();
    }
}
