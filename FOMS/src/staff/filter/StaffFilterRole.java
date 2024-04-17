package staff.filter;

import staff.Staff;
import staff.StaffDirectory;
import utils.IFilter;

import java.util.ArrayList;
import java.util.Objects;

import static staff.StaffView.displayStaffDetails;

public class StaffFilterRole implements IFilter {
    @Override
    public void filter(String role) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if(staff.getRole().getAcronym().equalsIgnoreCase(role)){
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
    }
}