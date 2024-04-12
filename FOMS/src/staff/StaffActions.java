package staff;

import java.util.ArrayList;
import exceptions.InvalidInputException;

public class StaffActions {
    public ArrayList<Staff> getStaffBranch(ArrayList<Staff> staffList, String branch) {
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffList) {
            if (staff.getRole() != StaffRoles.ADMIN && staff.getBranch().equalsIgnoreCase(branch)) {
                filteredList.add(staff);
            }
        }
        return filteredList;
    }

    public ArrayList<Staff> getStaffAge(ArrayList<Staff> staffList, int age)
    {
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffList) {
            if(staff.getAge() == age) {
                filteredList.add(staff);
            }
        }
        return filteredList;
    }

    public ArrayList<Staff> getStaffGender(ArrayList<Staff> staffList, char gender)
    {
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffList) {
            if(Character.toLowerCase(gender) == Character.toLowerCase(staff.getGender())) 
            {
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
    public ArrayList<Staff> getStaffRole(ArrayList<Staff> staffList, StaffRoles role)
    {
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffList) {
            if(staff.getRole() == role) 
            {
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}
