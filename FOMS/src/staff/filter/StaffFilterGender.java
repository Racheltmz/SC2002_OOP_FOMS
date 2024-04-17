package staff.filter;

import static staff.StaffView.*;

import java.util.ArrayList;
import java.util.Objects;

import exceptions.ExcelFileNotFound;
import staff.Staff;
import staff.StaffDirectory;
import utils.IFilter;

public class StaffFilterGender implements IFilter {
    @Override
    public void filter(String gender) {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            ArrayList<Staff> filteredList = new ArrayList<>();

            for (Staff staff : staffDirectory.getStaffDirectory()) {
                if (String.valueOf(staff.getGender()).equalsIgnoreCase(gender)) {
                    filteredList.add(staff);
                }
            }

            // Print details
            if (Objects.equals(gender, "M"))
                System.out.println("Male Staff Details");
            else if (Objects.equals(gender, "F"))
                System.out.println("Female Staff Details");

            displayStaffDetails(filteredList);
        } catch (ExcelFileNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

