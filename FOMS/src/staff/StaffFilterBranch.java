package staff;

import java.util.ArrayList;

public class StaffFilterBranch implements StaffFilter {
    @Override
    public ArrayList<Staff> getStaffList(ArrayList<Staff> staffList, String branch) {
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffList) {
            if(staff.getBranch().getName().equalsIgnoreCase(branch)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}