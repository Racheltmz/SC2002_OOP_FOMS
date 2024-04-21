package management.filter;

import management.Staff;
import management.StaffDirectory;
import utils.IFilter;

import java.util.ArrayList;

import static management.StaffView.displayStaffDetails;

public class StaffFilterAge implements IFilter {
    @Override
    public void filter(String age) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        int lowerBoundOfAge = Integer.parseInt(age);

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if(staff.getAge() >= lowerBoundOfAge){
                filteredList.add(staff);
            }
        }

        if (!filteredList.isEmpty()) {
            // Print details
            System.out.printf("Staff Details of staff whose age is equals to %s and above\n", age);
            displayStaffDetails(filteredList);
        }
        else {
            System.out.println("No current staff meet the filter requirements.");
        }
    }
}
