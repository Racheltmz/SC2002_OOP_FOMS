package staff.filter;

import staff.Staff;
import staff.StaffDirectory;
import utils.IFilter;

import java.util.ArrayList;

import static staff.StaffView.displayStaffDetails;

public class StaffFilterAge implements IFilter {
    @Override
    public void filter(String age) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if(staff.getAge() == Integer.parseInt(age)){
                filteredList.add(staff);
            }
        }
        // Print details
        System.out.printf("Staff Details of staff age %s\n", age);
        displayStaffDetails(filteredList);
    }
}
