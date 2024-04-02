package staff;

import java.util.ArrayList;

public class CriteriaBranch implements Criteria {
    @Override
    public ArrayList<Staff> getStaffList(ArrayList<Staff> staffList, String branch) {
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

        for (Staff staff : staffList) {
            if(staff.getBranch().equalsIgnoreCase(branch)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}