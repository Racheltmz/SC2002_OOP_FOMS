package staff.filter;

import static staff.StaffView.*;

import java.util.ArrayList;

import exceptions.ExcelFileNotFound;
import staff.Staff;
import staff.StaffDirectory;
import staff.StaffRoles;
import utils.IFilter;

public class StaffFilterBranch implements IFilter {
    @Override
    public void filter(String branch) {
        try {
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
        } catch (ExcelFileNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
