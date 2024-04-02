/* package Management;

import java.util.ArrayList;

public class CriteriaBranch implements CriteriaStr {
    @Override
    public ArrayList<Staff> meetCriteria(ArrayList<Staff> staffList, String branch) {
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

        for (Staff staff : staffList) {
            if(staff.getBranch().equalsIgnoreCase(branch)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
} */