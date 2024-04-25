package management;

import java.util.ArrayList;

import branch.Branch;
import exceptions.ItemNotFoundException;
import io.StaffXlsxHelper;

import static utils.ValidateHelper.validateString;

/**
 * Handles operations on Staff records and stores existing Staff records.
 */
public class StaffDirectory {

    private final ArrayList<Staff> staffDirectory;
    private static StaffDirectory staffSingleton = null;

    /**
     * Singleton constructor that reads all staff records from the storage file on initialisation.
     */
    private StaffDirectory() {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        this.staffDirectory = staffXlsxHelper.readFromXlsx();
    }

    /**
     * Gets the instance for the staff directory records.
     *
     * @return StaffDirectory instance.
     */
    public static StaffDirectory getInstance() {
        if (staffSingleton == null) {
            staffSingleton = new StaffDirectory();
        }
        return staffSingleton;
    }

    // Functionalities
    /**
     * Get all staff records.
     *
     * @return A list of all staff records.
     */
    public ArrayList<Staff> getStaffDirectory() {
        return this.staffDirectory;
    }

    /**
     * Get staff record by the staff ID.
     *
     * @param staffId Staff ID to find the staff.
     * @return The staff record to be found.
     */
    public Staff getStaffById(String staffId) {
        // Return staff object if it can be found
        for (Staff curStaff : this.staffDirectory) {
            if (curStaff.getStaffID().equalsIgnoreCase(staffId))
                return curStaff;
        }
        return null;
    }

    /**
     * Get staff record.
     *
     * @return Staff record that the user is looking for.
     */
    public Staff getStaff() {
        while (true) {
            // Get staff id
            String staffId = validateString("Enter staff ID: ");
            try {
                Staff staffFound = this.getStaffById(staffId);
                if (staffFound != null)
                    return staffFound;
                // Throw exception if staff cannot be found
                throw new ItemNotFoundException("Staff does not exist. Please enter a valid staffID.");
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
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

    /**
     * Add staff to the company.
     *
     * @param staff New staff record to add.
     * @param numExistingStaff Number of existing staff members in the company.
     */
    public void addStaff(Staff staff, int numExistingStaff) {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        this.staffDirectory.add(staff);
        staffXlsxHelper.writeToXlsx(staff, numExistingStaff);
    }

    /**
     * Edit staff account details.
     *
     * @param staffToUpdate Staff record to update.
     */
    public void updateStaff(Staff staffToUpdate) {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        staffXlsxHelper.updateXlsx(staffToUpdate);
    }

    /**
     * Remove staff account from xlsx file.
     *
     * @param staffToRmv Staff record to remove.
     */
    public void rmvStaff(Staff staffToRmv) {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        this.staffDirectory.removeIf(staff -> staff.getName().equals(staffToRmv.getName()));
        staffXlsxHelper.removeXlsx(staffToRmv.getId());
    }

    /**
     * Remove staff by branch.
     *
     * @param staffListRmv List of staff records to remove.
     */
    public void rmvStaffByBranch(ArrayList<Staff> staffListRmv) {
        StaffXlsxHelper staffXlsxHelper = StaffXlsxHelper.getInstance();
        for (Staff staffToRmv: staffListRmv) {
            this.staffDirectory.removeIf(staff -> staff.getName().equals(staffToRmv.getName()));
            staffXlsxHelper.removeXlsx(staffToRmv.getId());
        }
    }

    /**
     * Update staff's role from Staff to Manager.
     *
     * @param staff Staff record to update.
     */
    public void upgradeCredentials(Staff staff) {
        // Create new manager object
        Manager staffToManager = new Manager(staff.getStaffID(), staff.getName(), StaffRoles.MANAGER, staff.getGender(), staff.getAge(), staff.getBranch());
        // Remove staff
        rmvStaff(staff);
        // Add staff as a manager
        addStaff(staffToManager, this.getNumStaff());
    }

    /**
     * Get number of staff.
     *
     * @return The number of staff in this staffDirectory.
     */
    public int getNumStaff() {
        return this.staffDirectory.size();
    }

    /**
     * Get the number of a specific type of staff currently in the branch to check quota.
     *
     * @param branch Branch to check the manager quota from.
     * @param role Role to check for.
     * @return The number of staffs with the specified role in the specified branch.
     */
    public int getNumStaffBranchRole(Branch branch, StaffRoles role) {
        int count = 0;
        for (Staff staff: this.staffDirectory) {
            if (staff.getBranch() != null && staff.getBranch().equals(branch) && staff.getRole() == role) {
                count++;
            }
        }
        return count;
    }
}