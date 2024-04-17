package staff.filter;

import static staff.StaffView.*;

import java.util.ArrayList;
import java.util.Objects;

import exceptions.ExcelFileNotFound;
import staff.Staff;
import staff.StaffDirectory;
import utils.IFilter;

public class StaffFilterRole implements IFilter {
    @Override
    public void filter(String role) {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            ArrayList<Staff> filteredList = new ArrayList<>();

            for (Staff staff : staffDirectory.getStaffDirectory()) {
                if (staff.getRole().getAcronym().equalsIgnoreCase(role)) {
                    filteredList.add(staff);
                }
            }

            // Print details
            if (Objects.equals(role, "S"))
                System.out.println("Staff Details");
            else if (Objects.equals(role, "M"))
                System.out.println("Manager Details");
            else if (Objects.equals(role, "A"))
                System.out.println("Admin Details");
            displayStaffDetails(filteredList);
        } catch (ExcelFileNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
