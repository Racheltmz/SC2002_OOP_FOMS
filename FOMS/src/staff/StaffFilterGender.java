package staff;

import utils.Filter;

import java.util.ArrayList;
import java.util.Objects;

public class StaffFilterGender implements Filter {
    @Override
    public void filter(String gender) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if(String.valueOf(staff.getGender()).equalsIgnoreCase(gender)){
                filteredList.add(staff);
            }
        }
        // Print details
        if (Objects.equals(gender, "M"))
            System.out.println("Male Staff Details");
        else if (Objects.equals(gender, "F"))
            System.out.println("Female Staff Details");
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Role", "Gender", "Age");
        System.out.println("-".repeat(70));
        for (Staff staff : filteredList)
            staff.printStaffDetails();
    }
}
