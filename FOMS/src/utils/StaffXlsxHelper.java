package utils;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import staff.Admin;
import staff.Manager;
import staff.Staff;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exceptions.IllegalArgumentException;
import menu.MenuItem;
import staff.StaffRoles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        this.staffXlsx = getDataDir() + "staff_list.xlsx";
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
        Workbook workbook = null;
        try{
            workbook = WorkbookFactory.create(FileIOHelper.getFileInputStream(this.staffXlsx));
            Sheet sheet = workbook.getSheetAt(0);//Assuming first sheet i s where data is stored
            List<String[]> XlsxData = readAll(sheet, 1);
            ArrayList<Staff> staff = new ArrayList<>();
            if(XlsxData.isEmpty()) return staff;
            XlsxData.forEach((str) -> {
                try {
                    staff.add(new Staff(str));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            return staff;
        } finally {
            if(workbook != null){
                workbook.close();
            }
        }
    }

     /**
     * Writes to the XLSX File.
     *
     * @param items ArrayList of Menu items to save.
     * @throws IOException Unable to write to file.
     */
    public void writeToXlsx(ArrayList<Staff> staff) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Staff");
        String[] header = {"Name", "StaffID", "Role", "Gender", "Age","Branch"};
        writeHeader(header, sheet.createRow(0));
        for (int i = 0; i < staff.size(); i++) {
            writeRow(staff.get(i).toXlsx(), sheet.createRow(i + 1));
        }
        try (FileOutputStream fileOut = new FileOutputStream(this.staffXlsx)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    // Initialise staff records
    public ArrayList<Staff> initialiseRecords() {
        // Initialise a list
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        XSSFSheet staffSheet = getSheet(this.staffXlsx);
        // Iterate all rows
        for (int i = 1; i < staffSheet.getPhysicalNumberOfRows(); i++) {
            Row row = staffSheet.getRow(i);
            if (row != null && row.getLastCellNum() >= 6) { // Ensure at least 6 cells in a row
                // Get values from rows
                Cell staffIdCell = row.getCell(1);
                Cell nameCell = row.getCell(0);
                Cell roleCell = row.getCell(2);
                Cell genderCell = row.getCell(3);
                Cell ageCell = row.getCell(4);
                Cell branchCell = row.getCell(5);

                // Ensure cells are not empty
                if (staffIdCell != null && nameCell != null && roleCell != null && genderCell != null && ageCell != null && branchCell != null) {
                    // Convert to respective datatype
                    String staffId = staffIdCell.toString();
                    String name = nameCell.toString();
                    char role = roleCell.toString().charAt(0);
                    char gender = genderCell.toString().charAt(0);
                    int age = (int) ageCell.getNumericCellValue();
                    String branch = branchCell.toString();

                    // Add new staff
                    switch (role) {
                        case 'S':
                            staffList.add(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch));
                            break;
                        case 'M':
                            staffList.add(new Manager(staffId, name, StaffRoles.MANAGER, gender, age, branch));
                            break;
                        case 'A':
                            staffList.add(new Admin(staffId, name, StaffRoles.ADMIN, gender, age, branch));
                            break;
                    }
                }
            }
        }
        return staffList;
    }

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
