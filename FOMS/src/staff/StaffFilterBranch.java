package staff;

import utils.Filter;

import java.util.ArrayList;

public class StaffFilterBranch implements Filter {
    @Override
    public void filter(String branch) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if (staff.getRole() != StaffRoles.ADMIN && staff.getBranch().getName().equalsIgnoreCase(branch)) {
                filteredList.add(staff);
            }
        }
        // Print details
        System.out.printf("Staff Details of %s branch\n", branch);
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Role", "Gender", "Age");
        System.out.println("-".repeat(70));
        for (Staff staff : filteredList)
            staff.printStaffDetails();
    }
}