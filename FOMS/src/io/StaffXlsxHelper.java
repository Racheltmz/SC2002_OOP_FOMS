package io;

import branch.Branch;
import branch.BranchDirectory;
import staff.*;
import staff.Admin;
import staff.Manager;
import staff.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Helper class for staff
public class StaffXlsxHelper extends BaseXlsxHelper {
    /**
     * Path to Staff XLSX File in the data folder. Defaults to staff_list.xlsx
     */
    private final String staffXlsx = "staff_list.xlsx";

    private final String[] header = {"id", "name", "staffID", "role", "gender", "age", "branch"};

    /**
     * Default Constructor.
     */
    public StaffXlsxHelper() {}

    /**
     * Singleton instance StaffXlsxHelper.
     */
    private static StaffXlsxHelper staffInstance;

    /**
     * Gets the singleton instance of StaffXlsxHelper
     *
     * @return Instance of this class
     */
    public static StaffXlsxHelper getInstance() {
        if (staffInstance == null) staffInstance = new StaffXlsxHelper();
        return staffInstance;
    }

    /**
     * On initialisation, reads the XLSX file and parses it into an ArrayList of Staff objects
     *
     * @return ArrayList of Staff objects
     */
    public ArrayList<Staff> readFromXlsx() {
        // Initialise a list
        ArrayList<Staff> staffList = new ArrayList<>();
        BranchDirectory branchDirectory = BranchDirectory.getInstance();

        // Deserialize records
        List<String[]> XlsxData = deserializeRecords(this.staffXlsx, this.header,8, 1);
        if(XlsxData.isEmpty()) {
            serializeHeader(this.staffXlsx, this.header);
            return staffList;
        }

        // Add staff
        XlsxData.forEach((data) -> {
            UUID id = UUID.fromString(data[0]);
            // Convert to respective datatype
            String name = data[1];
            String staffId = data[2];
            char role = data[3].charAt(0);
            char gender = data[4].charAt(0);
            int age = (int) Double.parseDouble(data[5]);
            Branch branch = branchDirectory.getBranchByName(data[6]);
            String password = data[7];

            switch (role) {
                case 'S':
                    staffList.add(new Staff(id, staffId, name, StaffRoles.STAFF, gender, age, branch, password));
                    break;
                case 'M':
                    staffList.add(new Manager(id, staffId, name, StaffRoles.MANAGER, gender, age, branch, password));
                    break;
                case 'A':
                    staffList.add(new Admin(id, staffId, name, StaffRoles.ADMIN, gender, age, password));
                    break;
            }
        });
        return staffList;
    }

    /**
     * Writes a Staff record to the XLSX File.
     *
     * @param staff Staff record to add
     * @param numExistingRecords Number of existing staff records
     */
    public void writeToXlsx(Staff staff, int numExistingRecords) {
        serializeRecord(this.staffXlsx, staff.toXlsx(), numExistingRecords);
    }

    /**
     * Updates a Staff record in the XLSX File.
     *
     * @param staff Staff record to add
     */
    public void updateXlsx(Staff staff) {
        serializeUpdate(this.staffXlsx, staff.toXlsx(), staff.getId());
    }

    /**
     * Deletes a Staff record in the XLSX File.
     *
     * @param id ID of Staff record to delete
     */
    public void removeXlsx(UUID id) {
        deleteRecord(this.staffXlsx, id);
    }
}
