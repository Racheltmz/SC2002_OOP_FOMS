package utils;


import staff.Staff;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exceptions.IllegalArgumentException;
import menu.MenuItem;

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
    private String staffXlsx = "staff_list.xlsx";

    /**
     * Singleton instance of this class
     */
    private static StaffXlsxHelper mInstance;

    /**
     * Private constructor to initialize this class with staff.xlsx as the XLSX file
     */
    private StaffXlsxHelper() {}

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
            if(XlsxData.size()==0) return staff;
            XlsxData.forEach((str) -> {
                try {
                    staff.add(new Staff(str));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            return staff;
        }finally {
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
