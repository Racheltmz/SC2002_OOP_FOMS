package Management;

import java.util.ArrayList;

public class CriteriaRole implements CriteriaStr {
    @Override
    public ArrayList<Staff> meetCriteria(ArrayList<Staff> staffList, String role) {
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

        for (Staff staff : staffList) {
            if(staff.getRole().getName().equalsIgnoreCase(role)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}