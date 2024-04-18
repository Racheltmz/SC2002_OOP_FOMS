package management.filter;

import management.Staff;
import management.StaffDirectory;
import management.StaffRoles;
import utils.IFilter;

import java.util.ArrayList;

import static management.StaffView.displayStaffDetails;

public class StaffFilterBranch implements IFilter {
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
        displayStaffDetails(filteredList);
    }
}