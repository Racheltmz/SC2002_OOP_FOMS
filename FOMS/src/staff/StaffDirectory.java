package staff;

import static authorisation.Authorisation.*;

import java.util.ArrayList;
import java.util.Objects;

import branch.Branch;
import exceptions.ExcelFileNotFound;
import exceptions.ItemNotFoundException;
import io.StaffXlsxHelper;
import utils.InputScanner;

// Records of staff
public class StaffDirectory {
    // Attribute
    private final ArrayList<Staff> staffDirectory;
    private static StaffDirectory staffSingleton = null;

    private StaffDirectory() throws ExcelFileNotFound {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        this.staffDirectory = staffXlsxHelper.readFromXlsx();
    }

    public static StaffDirectory getInstance() throws ExcelFileNotFound {
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

    public Staff getStaffById(String staffId) {
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
        return null;
    }

    // Get staff based on staff ID
    public Staff getStaff() {
        InputScanner sc = InputScanner.getInstance();
        while (true) {
            // Get staff id
            System.out.print("\nEnter staff ID: ");
            String staffId = sc.next();
            Staff staffFound = this.getStaffById(staffId);
            if (staffFound != null)
                return staffFound;
        }
    }

    /**
     * Check if staffID exists ensure unique staff records.
     *
     * @param staffId Staff ID to check.
     * @return Boolean of whether staff exists.
     */
    public boolean staffExistsByStaffID(String staffId) {
        Staff staffFound = this.getStaffById(staffId);
        return staffFound != null;
    }

    // Add staff (admin purposes)
    public void addStaff(Staff staff, int numExistingStaff, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
            this.staffDirectory.add(staff);
            staffXlsxHelper.writeToXlsx(staff, numExistingStaff);
        }
    }

    public void updateStaff(Staff staffToUpdate) {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        staffXlsxHelper.updateXlsx(staffToUpdate);
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

    public void upgradeCredentials(Staff staff, StaffRoles auth) throws Exception {
        if (authoriseAdmin(auth)) {
            // Create new manager object
            Manager staffToManager = new Manager(staff.getStaffID(), staff.getName(), StaffRoles.MANAGER, staff.getGender(), staff.getAge(), staff.getBranch());
            // Remove staff
            rmvStaff(staff, auth);
            // Add staff as a manager
            addStaff(staffToManager, this.getNumStaff(), auth);
        }
    }

    // Get number of staff
    public int getNumStaff() {
        return this.staffDirectory.size();
    }

    // Get number of managers to check quota
    // TODO: AFREEN can consider shortening the name but rmb to ensure consistency throughout all files
    public int getNumManagersByBranch(Branch branch, StaffRoles auth) {
        int count = 0;
        if (authoriseAdmin(auth)) {
            for (Staff staff: this.staffDirectory) {
                if (staff.getRole() == StaffRoles.MANAGER && staff.getBranch().equals(branch)) {
                    count++;
                }
            }
        }
        return count;
    }
}
