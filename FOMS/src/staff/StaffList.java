package staff;

import java.util.ArrayList;
import exceptions.InvalidInputException;

public class StaffList {
    public ArrayList<Staff> getStaffBranch(ArrayList<Staff> staffList, String branch) throws InvalidInputException{
    if (branch == null || branch.isEmpty()) {
        throw new InvalidInputException("Branch name cannot be empty or null");
    }
    {
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

        for (Staff staff : staffList) {
            if(staff.getBranch().equalsIgnoreCase(branch)){
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}

    public ArrayList<Staff> getStaffAge(ArrayList<Staff> staffList, int age)
    {
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

        for (Staff staff : staffList) {
            if(staff.getAge() == age) {
                filteredList.add(staff);
            }
        }
        return filteredList;
    }

    public ArrayList<Staff> getStaffGender(ArrayList<Staff> staffList, char gender)
    {
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

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
        ArrayList<Staff> filteredList = new ArrayList<Staff>();

        for (Staff staff : staffList) {
            if(staff.getRole() == role) 
            {
                filteredList.add(staff);
            }
        }
        return filteredList;
    }
}
