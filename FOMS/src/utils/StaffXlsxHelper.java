package utils;

import staff.Admin;
import staff.Manager;
import staff.Staff;

import staff.StaffRoles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Helper class for staff
public class StaffXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Staff XLSX File in the data folder. Defaults to staff_list.xlsx
     */
    private final String staffXlsx;

    private final String[] header = {"id", "name", "staffID", "role", "gender", "age", "branch"};

    /**
     * Default Constructor to initialize this class with staff.xlsx as the XLSX file.
     */
    public StaffXlsxHelper() {
        this.staffXlsx = "staff_list.xlsx";
    }

    /**
     * Singleton instance of this class
     */
    private static StaffXlsxHelper mInstance;

    /**
     * Gets the singleton instance of StaffXlsxHelper
     *
     * @return Instance of this class
     */
    public static StaffXlsxHelper getInstance() {
        if (mInstance == null) mInstance = new StaffXlsxHelper();
        return mInstance;
    }

    /**
     * On initialisation, reads the XLSX file and parses it into an ArrayList of Staff objects
     *
     * @return ArrayList of Staff objects
     */
    public ArrayList<Staff> readFromXlsx() {
        List<String[]> XlsxData = deserializeRecords(this.staffXlsx, this.header,7, 1);
        ArrayList<Staff> staffList = new ArrayList<>();
        if(XlsxData.isEmpty()) {
            serializeHeader(this.staffXlsx, this.header);
            return staffList;
        }
        XlsxData.forEach((data) -> {
            UUID id = UUID.fromString(data[0]);
            // Convert to respective datatype
            String name = data[1];
            String staffId = data[2];
            String role = data[3];
            char gender = data[4].charAt(0);
            int age = (int) Double.parseDouble(data[5]);
            String branch = data[6];

            // Add new staff
            switch (role) {
                case "S":
                    staffList.add(new Staff(id, staffId, name, StaffRoles.STAFF, gender, age, branch));
                    break;
                case "M":
                    staffList.add(new Manager(id, staffId, name, StaffRoles.MANAGER, gender, age, branch));
                    break;
                case "A":
                    staffList.add(new Admin(id, staffId, name, StaffRoles.ADMIN, gender, age));
                    break;
            }
        });
        return staffList;
    }

    /**
     * Writes a staff record to the XLSX File.
     *
     * @param staff Staff record to add
     * @param numExistingRecords Number of existing staff records
     */
    public void writeToXlsx(Staff staff, int numExistingRecords) {
        serializeRecord(this.staffXlsx, staff.toXlsx(), numExistingRecords);
    }

    /**
     * Updates a staff record in the XLSX File.
     *
     * @param staff Staff record to add
     */
    public void updateXlsx(Staff staff) {
        serializeUpdate(this.staffXlsx, staff.toXlsx(), staff.getId());
    }

    /**
     * Deletes a staff record in the XLSX File.
     *
     * @param id ID of staff record to delete
     */
    public void removeXlsx(UUID id) {
        deleteRecord(this.staffXlsx, id);
    }
}
