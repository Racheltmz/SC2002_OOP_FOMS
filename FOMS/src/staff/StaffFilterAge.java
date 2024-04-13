package staff;

import utils.Filter;

import java.util.ArrayList;

public class StaffFilterAge implements Filter {
    @Override
    public void filter(String age) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        ArrayList<Staff> filteredList = new ArrayList<>();

        for (Staff staff : staffDirectory.getStaffDirectory()) {
            if(staff.getAge() == Integer.parseInt(age)){
                filteredList.add(staff);
            }
        }
        // Print details
        System.out.printf("Staff Details of staff age %s\n", age);
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Role", "Gender", "Age");
        System.out.println("-".repeat(70));
        for (Staff staff : filteredList)
            staff.printStaffDetails();
    }
}
