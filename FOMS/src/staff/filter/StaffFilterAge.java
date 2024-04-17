package staff.filter;

import static staff.StaffView.*;

import java.util.ArrayList;

import exceptions.ExcelFileNotFound;
import staff.Staff;
import staff.StaffDirectory;
import utils.IFilter;

public class StaffFilterAge implements IFilter {
    @Override
    public void filter(String age) {
        try {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            ArrayList<Staff> filteredList = new ArrayList<>();

            for (Staff staff : staffDirectory.getStaffDirectory()) {
                if (staff.getAge() == Integer.parseInt(age)) {
                    filteredList.add(staff);
                }
            }

            // Print details
            System.out.printf("Staff Details of staff age %s\n", age);
            displayStaffDetails(filteredList);
        } catch (ExcelFileNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
