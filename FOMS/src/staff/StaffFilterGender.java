package staff;

import java.util.ArrayList;

public class StaffFilterGender implements StaffFilter {
    @Override
    public ArrayList<Staff> getStaffList(ArrayList<Staff> staffList, String gender) {
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffList) {
            if(String.valueOf(staff.getGender()).equalsIgnoreCase(gender)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}
