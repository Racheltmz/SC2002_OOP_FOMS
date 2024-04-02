package staff;

import java.util.ArrayList;

public class CriteriaRole implements Criteria {
    @Override
    public ArrayList<Staff> getStaffList(ArrayList<Staff> staffList, String role) {
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

        for (Staff staff : staffList) {
            if(staff.getRole().getName().equalsIgnoreCase(role)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}