package management.filter;

import management.Staff;
import management.StaffDirectory;
import utils.IFilter;

import java.util.ArrayList;
import java.util.Objects;

import static management.StaffView.displayStaffDetails;

/**
 * StaffFilterGender class implementing the method to filter staff by their gender.
 */
public class StaffFilterGender implements IFilter {
    /**
     * The implemented filter method for filtering staff by their gender.
     *
     * @param gender The gender to filter the staff by.
     */
    @Override
    public void filter(String gender) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if(String.valueOf(staff.getGender()).equalsIgnoreCase(gender)){
                filteredList.add(staff);
            }
        }

        if (!filteredList.isEmpty()) {
            // Print details
            if (Objects.equals(gender, "M"))
                System.out.println("Male Staff Details");
            else if (Objects.equals(gender, "F"))
                System.out.println("Female Staff Details");

            displayStaffDetails(filteredList);
        }
        else {
            System.out.println("No current staff meet the filter requirements.");
        }
    }
}
