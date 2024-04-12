package staff;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import branch.BranchDirectory;
import exceptions.ItemNotFoundException;
import menu.Menu;
import utils.StaffXlsxHelper;

import static authorisation.Authorisation.authoriseAdmin;

// Records of staff
public class StaffDirectory {
    // Attribute
    private final ArrayList<Staff> staffDirectory;
    private static StaffDirectory staffSingleton = null;

    private StaffDirectory() {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        this.staffDirectory = staffXlsxHelper.readFromXlsx();
    }

    public static StaffDirectory getInstance() {
        if (staffSingleton == null) {
            staffSingleton = new StaffDirectory();
        }
        return staffSingleton;
    }

    // Functionalities
    // Get all staff
    public ArrayList<Staff> getStaffDirectory() {
        return this.staffDirectory;
    }

    // Display staff details
    public void displayAllStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            // Print details
            System.out.println("Staff Details:");
            System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Branch", "Role", "Gender", "Age");
            System.out.println("-".repeat(80));
            for (Staff curStaff : this.staffDirectory) {
                curStaff.getStaffDetails();
            }
        }
    }

    // Get staff based on staff ID
    public Staff getStaff(String staffId) {
        try {
            // Return staff object if it can be found
            for (Staff curStaff : this.staffDirectory) {
                if (Objects.equals(curStaff.getStaffID(), staffId))
                    return curStaff;
            }
            // Return null if staff cannot be found
            throw new ItemNotFoundException("Staff does not exist. Please enter a valid staffID.");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Add staff (admin purposes)
    public void addStaff(Staff staff, int numExistingStaff, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            this.staffDirectory.add(staff);
            System.out.println(staff.getStaffID());
            staffXlsxHelper.writeToXlsx(staff, numExistingStaff);
        }
    }

    public void updateStaff(Staff staffToUpdate, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            staffXlsxHelper.updateXlsx(staffToUpdate);
        }
    }

    // Remove staff (admin purposes) (TO CHECK)
    public void rmvStaff(UUID recordID, String staffID, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            this.staffDirectory.remove(this.getStaff(staffID));
            staffXlsxHelper.removeXlsx(recordID);
        }
    }

    // Filters
    public ArrayList<Staff> filterBranch(String branch) {
        StaffActions toFilter = new StaffActions();
        return toFilter.getStaffBranch(this.staffDirectory, branch);
    }

    public ArrayList<Staff> filterRole(StaffRoles role) {
        StaffActions toFilter = new StaffActions();
        return toFilter.getStaffRole(this.staffDirectory, role);
    }

    // Get number of staff
    public int getNumStaff() {
        return this.staffDirectory.size();
    }

    // Get number of managers to check quota
    public int getNumManagers(StaffRoles auth) {
        if (authoriseAdmin(auth))
            return this.filterRole(StaffRoles.MANAGER).size();
        return 0;
    }
}
