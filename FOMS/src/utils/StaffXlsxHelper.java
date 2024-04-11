package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import staff.Admin;
import staff.Manager;
import staff.Staff;

import staff.StaffRoles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static utils.FileIOHelper.getSheet;

// Helper class for staff
public class StaffXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Staff XLSX File in the data folder. Defaults to staff_list.xlsx
     */
    private final String staffXlsx;

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
        XSSFSheet sheet = getSheet(this.staffXlsx);
        List<String[]> XlsxData = deserializeRecords(sheet, 1);
        ArrayList<Staff> staffList = new ArrayList<>();
        if(XlsxData.isEmpty()) return staffList;
        XlsxData.forEach((data) -> {
            if (data.length == 7) {
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
                        staffList.add(new Admin(id, staffId, name, StaffRoles.ADMIN, gender, age, branch));
                        break;
                }
            }
        });
        return staffList;
    }

    /**
     * Writes a staff record to the XLSX File.
     *
     * @param staff Staff record to add
     * @param numExistingRecords Number of existing staff records
     * @throws IOException Error if there is an issue with IO processes
     */
    public void writeToXlsx(Staff staff, int numExistingRecords) throws IOException {
        String[] header = {"id", "name", "staffID", "role", "gender", "age", "branch"};
        serializeRecord(this.staffXlsx, staff.toXlsx(), header, numExistingRecords);
    }

    /**
     * Updates a staff record in the XLSX File.
     *
     * @param staff Staff record to add
     * @throws IOException Error if there is an issue with IO processes
     */
    public void updateXlsx(Staff staff) throws IOException {
        serializeUpdate(this.staffXlsx, staff.toXlsx(), staff.getId());
    }

    /**
     * Deletes a staff record in the XLSX File.
     *
     * @param id ID of staff record to delete
     * @throws IOException Error if there is an issue with IO processes
     */
    public void removeXlsx(UUID id) throws IOException {
        deleteRecord(this.staffXlsx, id);
    }
}
