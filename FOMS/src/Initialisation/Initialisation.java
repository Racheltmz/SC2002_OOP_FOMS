package Initialisation;

import Management.Company;
import Management.Branch;
import Management.Admin;
import Management.Manager;
import Management.Staff;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

// Initialisation tasks
public class Initialisation {
    /* LOAD STAFF LIST ON INITIALISATION */
    public Company initialiseCompany() {
        ArrayList<Staff> staffList = initialiseStaffRecords();
         ArrayList<Branch> branchList = initialiseBranchRecords();
        return new Company(staffList, branchList);
    }

    public XSSFSheet getSheet(String filePath) {
        XSSFSheet sheet = null;
        try {
            // Reading file from local directory
            FileInputStream file = new FileInputStream(new File(filePath));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first sheet from the workbook
            sheet = workbook.getSheetAt(0);

            // Closing workbook and file output streams
            workbook.close();
            file.close();
        } catch (Exception e) {
            // Display the exception along with line number
            // using printStackTrace() method
            System.out.println("Error when loading file, please check the file.");
        }
        return sheet;
    }

    public ArrayList<Staff> initialiseStaffRecords() {
        // Initialise a list
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        XSSFSheet sheet = getSheet("./data/staff_list.xlsx");
        try {
            // Iterate all rows
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                // Get values from rows
                String staffId = row.getCell(1).toString();
                String name = row.getCell(0).toString();
                char role = row.getCell(2).toString().charAt(0);
                char gender = row.getCell(3).toString().charAt(0);
                int age = (int) row.getCell(4).getNumericCellValue();
                String branch = row.getCell(5).toString();
                Staff.Roles roleCat;

                // Add new staff
                switch (role) {
                    case 'S':
                        roleCat = Staff.Roles.STAFF;
                        Staff staff = new Staff(staffId, name, roleCat, gender, age, branch);
                        staffList.add(staff);
                        break;
                    case 'M':
                        roleCat = Staff.Roles.MANAGER;
                        Manager manager = new Manager(staffId, name, roleCat, gender, age, branch);
                        staffList.add(manager);
                        break;
                    case 'A':
                        roleCat = Staff.Roles.ADMIN;
                        Admin admin = new Admin(staffId, name, roleCat, gender, age, branch);
                        staffList.add(admin);
                        break;
                }
            }
        } catch (NullPointerException nullPointerException) {
            // Alert if a record has an empty cell (TBC)
            System.out.println("Record was not inserted as it has an empty cell.");
        }
        return staffList;
    }

    public ArrayList<Branch> initialiseBranchRecords() {
        // Initialise a list
        ArrayList<Branch> branchList = new ArrayList<Branch>();
        XSSFSheet sheet = getSheet("./data/branch_list.xlsx");
        try {
            // Iterate all rows
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                // Get values from rows
                String name = row.getCell(0).toString();
                String location = row.getCell(1).toString();
                int staffQuota = (int) row.getCell(2).getNumericCellValue();

                // Add new branch
                Branch branch = new Branch(name, location, staffQuota);
                branchList.add(branch);
            }
        } catch (NullPointerException nullPointerException) {
            // Alert if a record has an empty cell (TBC)
            System.out.println("Record was not inserted as it has an empty cell.");
        }
        return branchList;
    }
}
