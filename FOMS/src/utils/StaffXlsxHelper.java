package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import staff.Admin;
import staff.Manager;
import staff.Staff;

import staff.StaffRoles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.FileIOHelper.getSheet;

// Helper class for staff
public class StaffXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Staff XLSX File in the data folder. Defaults to staff_list.xlsx
     */
    private String staffXlsx;

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
     * Reads the XLSX file and parses it into an ArrayList of Staff objects
     *
     * @return ArrayList of Staff objects
     * @throws IOException Unable to read from file
     */
    public ArrayList<Staff> readFromXlsx() throws IOException {
        XSSFSheet sheet = getSheet(this.staffXlsx);
        List<String[]> XlsxData = deserializeRecords(sheet, 1);
        ArrayList<Staff> staffList = new ArrayList<>();
        if(XlsxData.isEmpty()) return staffList;
        XlsxData.forEach((data) -> {
            if (data.length == 6) {
                // Convert to respective datatype
                String name = data[0];
                String staffId = data[1];
                String role = data[2];
                char gender = data[3].charAt(0);
                int age = (int) Double.parseDouble(data[4]);
                String branch = data[5];

                // Add new staff
                switch (role) {
                    case "S":
                        staffList.add(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch));
                        break;
                    case "M":
                        staffList.add(new Manager(staffId, name, StaffRoles.MANAGER, gender, age, branch));
                        break;
                    case "A":
                        staffList.add(new Admin(staffId, name, StaffRoles.ADMIN, gender, age, branch));
                        break;
                }
            }
        });
        return staffList;
    }

    /**
     * Writes to the XLSX File.
     * @param staff
     * @throws IOException
     */
    // Insert record
    public void writeToXlsx(Staff staff, int numExistingRecords) throws IOException {
        String[] header = {"id", "name", "staffID", "role", "gender", "age", "branch"};
        serializeRecord(this.staffXlsx, staff.toXlsx(), header, numExistingRecords);
    }

    // Update record
    public void updateXlsx(Staff staff, int idxToUpdate) throws IOException {
        serializeUpdate(this.staffXlsx, staff.toXlsx(), idxToUpdate);
    }

//    public void writeToXlsx(ArrayList<Staff> staff) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Staff");
//        String[] header = {"Name", "StaffID", "Role", "Gender", "Age","Branch"};
//        writeHeader(header, sheet.createRow(0));
//        for (int i = 0; i < staff.size(); i++) {
//            writeRow(staff.get(i).toXlsx(), sheet.createRow(i + 1));
//        }
//        try (FileOutputStream fileOut = new FileOutputStream(this.staffXlsx)) {
//            workbook.write(fileOut);
//        } finally {
//            workbook.close();
//        }
//    }
//    private void writeHeader(String[] header, Row row) {
//        for (int i = 0; i < header.length; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(header[i]);
//        }
//    }
//
//    private void writeRow(String[] data, Row row) {
//        for (int i = 0; i < data.length; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(data[i]);
//        }
//    }
}
