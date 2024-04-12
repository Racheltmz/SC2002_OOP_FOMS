package staff;

import java.util.ArrayList;

public class StaffFilterAge implements StaffFilter {
    @Override
    public ArrayList<Staff> getStaffList(ArrayList<Staff> staffList, String age) {
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffList) {
            if(staff.getAge() == Integer.parseInt(age)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}
