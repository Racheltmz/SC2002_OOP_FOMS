// Import
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class FOMSApp {
    public static void main(String[] args) throws IOException {
        // Try block to check for exceptions
        try {

            // Reading file from local directory
            FileInputStream file = new FileInputStream(new File("./data/staff_list.xlsx"));

            // Create Workbook instance holding reference to
            // .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            // Initialise a list
            List<Staff> staffList = new ArrayList<Staff>();

            // Iterate all rows
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                String staffId = row.getCell(1).toString();
                String name = row.getCell(0).toString();
                char role = row.getCell(2).toString().charAt(0);
                Staff.Roles roleCat = Staff.Roles.STAFF;
                switch (role) {
                    case 'S':
                        roleCat = Staff.Roles.STAFF;
                        break;
                    case 'M':
                        roleCat = Staff.Roles.MANAGER;
                        break;
                    case 'A':
                        roleCat = Staff.Roles.ADMIN;
                        break;
                }
                char gender = row.getCell(3).toString().charAt(0);
                int age = (int) row.getCell(4).getNumericCellValue();
                String branch = row.getCell(5).toString();
                Staff staff = new Staff(staffId, name, roleCat, gender, age, branch);
                staff.getStaff();
                // Append new staff
                staffList.add(staff);
            }
            // Closing file output streams
            file.close();
        }

        // Catch block to handle exceptions
        catch (NullPointerException nullPointerException) {
            System.out.println("Record was not inserted as it has an empty cell.");
        }
        catch (Exception e) {
            // Display the exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }
}