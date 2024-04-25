package management.filter;

import management.Staff;
import management.StaffDirectory;
import management.StaffRoles;
import utils.IFilter;

import java.util.ArrayList;

import static management.StaffView.displayStaffDetails;

/**
 * StaffFilterBranch class implementing the method to filter staff by their Branch.
 */
public class StaffFilterBranch implements IFilter {
    /**
     * The implemented filter method for filtering staff by their Branch.
     *
     * @param branch The branch to filter the staff by.
     */
    @Override
    public void filter(String branch) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if (staff.getRole() != StaffRoles.ADMIN && staff.getBranch().getName().equalsIgnoreCase(branch)) {
                filteredList.add(staff);
            }
        }

        if (!filteredList.isEmpty()) {
            // Print details
            System.out.printf("Staff Details of %s branch\n", branch);
            displayStaffDetails(filteredList);
        }
        else {
            System.out.println("No current staff meet the filter requirements.");
        }
    }
}