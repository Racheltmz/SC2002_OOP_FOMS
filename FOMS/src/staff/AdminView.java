package staff;

import staff.filter.StaffFilterOptions;
import utils.InputScanner;

import java.util.ArrayList;

import static authorisation.Authorisation.authoriseAdmin;
import static staff.StaffView.displayStaffDetails;

public class AdminView implements IAdminView{
    private InputScanner sc = InputScanner.getInstance();

    @Override
    public StaffFilterOptions getFieldToFilter() {
        System.out.println("Enter the number for the field you want to filter the staff list by: ");
        System.out.println("1. Branch");
        System.out.println("2. Role");
        System.out.println("3. Gender");
        System.out.println("4. Age");
        int adminChoice = sc.nextInt();
        return StaffFilterOptions.of(adminChoice);
    }

    @Override 
    public void displayAllStaff(ArrayList<Staff> staffDirectory, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            // Print details
            System.out.println("Staff Details:");
            ArrayList<Staff> staffList = new ArrayList<>();
            for (Staff staff : staffDirectory) {
                if (staff.getRole() != StaffRoles.ADMIN) {
                    staffList.add(staff);
                }
            }
            displayStaffDetails(staffList);
        }
    }
}
