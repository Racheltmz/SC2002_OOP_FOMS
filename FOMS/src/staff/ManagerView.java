package staff;

import branch.Branch;

import java.util.ArrayList;

// TODO: combine with filter
// Boundary for staff information
public class ManagerView {
    public void displayStaffByBranch(Branch branch) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // Get branch name
        String branchName = branch.getName();
        // Filter staff by branch
        ArrayList<Staff> staffList = staffDirectory.filterBranch(branchName);
        // Print details
        System.out.printf("Staff Details from %s branch:\n", branchName);
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Role", "Gender", "Age");
        System.out.println("-".repeat(70));
        for (Staff staff : staffList)
            staff.getStaffDetails();
    }
}
