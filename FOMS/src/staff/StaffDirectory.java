package staff;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import branch.Branch;
import exceptions.ItemNotFoundException;
import utils.Filter;
import utils.InputScanner;
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
    public void displayAllStaff() {
        // Print details
        System.out.println("Staff Details:");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-8s | %-5s |\n", "StaffID", "Name", "Branch", "Role", "Gender", "Age");
        System.out.println("-".repeat(80));
        for (Staff curStaff : this.staffDirectory) {
            curStaff.printStaffDetails();
        }
    }

    // Get staff based on staff ID
    public Staff getStaff() {
        InputScanner sc = InputScanner.getInstance();
        while (true) {
            // Get staff id
            System.out.print("\nEnter staff ID: ");
            String staffId = sc.next();
            try {
                // Return staff object if it can be found
                for (Staff curStaff : this.staffDirectory) {
                    if (Objects.equals(curStaff.getStaffID(), staffId))
                        return curStaff;
                }
                // Throw exception if staff cannot be found
                throw new ItemNotFoundException("Staff does not exist. Please enter a valid staffID.");
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Add staff (admin purposes)
    public void addStaff(Staff staff, int numExistingStaff, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            this.staffDirectory.add(staff);
            staffXlsxHelper.writeToXlsx(staff, numExistingStaff);
        }
    }

    public void updateStaff(Staff staffToUpdate, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            staffXlsxHelper.updateXlsx(staffToUpdate);
        }
    }

    // Remove staff (admin purposes)
    public void rmvStaff(Staff staffToRmv, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            this.staffDirectory.removeIf(staff -> staff.getName().equals(staffToRmv.getName()));
            staffXlsxHelper.removeXlsx(staffToRmv.getId());
        }
    }

    public void rmvStaffByBranch(ArrayList<Staff> staffListRmv, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            for (Staff staffToRmv: staffListRmv) {
                this.staffDirectory.removeIf(staff -> staff.getName().equals(staffToRmv.getName()));
                staffXlsxHelper.removeXlsx(staffToRmv.getId());
            }
        }
    }

    public void upgradeCredentials(Staff staff, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            // Create new manager object
            Manager staffToManager = new Manager(staff.getStaffID(), staff.getName(), StaffRoles.MANAGER, staff.getGender(), staff.getAge(), staff.getBranch());
            // Remove staff
            rmvStaff(staff, auth);
            // Add staff as a manager
            addStaff(staffToManager, this.getNumStaff(), auth);
        }
    }

    // TODO: Discuss filters
    // // Filters
    // public ArrayList<Staff> filterBranch(String branch) {
    //     StaffActions toFilter = new StaffActions();
    //     return toFilter.getStaffBranch(this.staffDirectory, branch);
    // }

    // public ArrayList<Staff> filterRole(StaffRoles role) {
    //     StaffActions toFilter = new StaffActions();
    //     return toFilter.getStaffRole(this.staffDirectory, role);
    // }

    // Get number of staff
    public int getNumStaff() {
        return this.staffDirectory.size();
    }

    // Get number of managers to check quota
    public int getNumManagers(StaffRoles auth) {
        int count = 0;
        if (authoriseAdmin(auth)) {
            for (Staff staff: this.staffDirectory) {
                if (staff.getRole() == StaffRoles.MANAGER) {
                    count++;
                }
            }
        }
        return count;
    }
}
