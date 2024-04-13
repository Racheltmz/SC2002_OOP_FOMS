package staff;

import utils.Filter;

import java.util.ArrayList;
import java.util.Objects;

public class StaffFilterRole implements Filter {
    @Override
    public void filter(String role) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if(staff.getRole().getAcronym().equalsIgnoreCase(role)){
                filteredList.add(staff);
            }
        }
        // TODO: AFREEN work out smtg with the StaffRoles enum such that this can be done in a single print statement (map the acronym and the name as well)
        // Print details
        if (Objects.equals(role, "S"))
            System.out.println("Staff Details");
        else if (Objects.equals(role, "M"))
            System.out.println("Manager Details");
        else if (Objects.equals(role, "A"))
            System.out.println("Admin Details");
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Role", "Gender", "Age");
        System.out.println("-".repeat(70));
        for (Staff staff : filteredList)
            staff.printStaffDetails();
    }
}