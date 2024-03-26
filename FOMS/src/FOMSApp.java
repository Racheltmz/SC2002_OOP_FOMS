// Import
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class FOMSApp {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Try block to check for exceptions
        try {

            // Reading file from local directory
            FileInputStream staffFile = new FileInputStream(new File("./data/staff_list.xlsx"));

            // Create Workbook instance holding reference to
            // .xlsx file
            XSSFWorkbook staffWorkbook = new XSSFWorkbook(staffFile);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = staffWorkbook.getSheetAt(0);

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
            // Closing workbook and file output streams
            staffWorkbook.close();
            staffFile.close();

            // Reading menu list file from local directory
            FileInputStream menuFile = new FileInputStream(new File("./data/menu_list.xlsx"));
            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook menuWorkbook = new XSSFWorkbook(menuFile);
            // Get the first/desired sheet from the workbook
            XSSFSheet menuSheet = menuWorkbook.getSheetAt(0);

            // Initialise a list for menu items
            List<Menu> menuList = new ArrayList<>();

                    // Iterate through menu list rows
            for (int i = 1; i < menuSheet.getPhysicalNumberOfRows(); i++) {
                Row row = menuSheet.getRow(i);
                // Extracting menu details from the row
                String name = row.getCell(0).toString();
                double price = row.getCell(1).getNumericCellValue();
                String branchCode = row.getCell(2).toString(); // Get the branch code as a string

                final Menu.Branch branch = null;

                // Check the value of branchCode using equalsIgnoreCase method
                if (branchCode.equalsIgnoreCase("NTU")) {
                    System.out.println ("NTU menu");
                } else if (branchCode.equalsIgnoreCase("JE")) {
                    System.out.println ("NTU menu");
                } else if (branchCode.equalsIgnoreCase("JP")) {
                    System.out.println ("NTU menu");
                }
            

            String category = row.getCell(3).toString();
            // Creating Menu object
            Menu menu = new Menu( name,category, price, branchCode, category);
            menu.getMenuId();
            menuList.add(menu);
        

            // Closing menu workbook and file streams
            menuWorkbook.close();
            menuFile.close();

                        }
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