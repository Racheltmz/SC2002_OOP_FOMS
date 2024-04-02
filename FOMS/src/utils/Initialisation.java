package utils;

import management.Company;
import branch.Branch;
import staff.Admin;
import staff.Manager;
import staff.Staff;
import staff.StaffRoles;
import menu.Menu;
import menu.MenuItem;
import payment.Payment;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

// Initialise all company-related directories
public class Initialisation {
    // Initialise all directories
    public static Company initialiseCompany() {
        ArrayList<Staff> staffList = initialiseStaffRecords();
        ArrayList<Branch> branchList = new ArrayList<>();
        ArrayList<Menu> menuList = new ArrayList<>();
        initialiseBranchRecords(branchList, menuList);
        ArrayList<Payment> paymentList = initialisePaymentRecords();
        Company company = new Company(staffList, branchList, paymentList, menuList);
        initialiseMenus(company);
        return company;
    }

    // Get Excel spreadsheet by filename
    public static XSSFSheet getSheet(String filePath) {
        XSSFSheet sheet = null;
        try {
            // Reading file from local directory
            FileInputStream file = new FileInputStream(filePath);

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first sheet from the workbook
            sheet = workbook.getSheetAt(0);

            // Closing workbook and file output streams
            workbook.close();
            file.close();
        } catch (IOException ioException) {
            System.out.println("Error reading the Excel file: " + ioException.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sheet;
    }

    // Initialise staff records
    public static ArrayList<Staff> initialiseStaffRecords() {
        // Initialise a list
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        XSSFSheet staffSheet = getSheet("./data/staff_list.xlsx");
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

    // Initialise branch records and menu records based on the branch
    public static void initialiseBranchRecords(ArrayList<Branch> branchList, ArrayList<Menu> menuList) {
        XSSFSheet branchSheet = getSheet("./data/branch_list.xlsx");
        // Iterate all rows
        for (int i = 1; i < branchSheet.getPhysicalNumberOfRows(); i++) {
            Row row = branchSheet.getRow(i);
            if (row != null && row.getLastCellNum() >= 3) { // Ensure at least 3 cells in a row
                // Get values from rows
                Cell nameCell = row.getCell(0);
                Cell locCell = row.getCell(1);
                Cell quotaCell = row.getCell(2);

                // Ensure cells are not empty
                if (nameCell != null && locCell != null && quotaCell != null) {
                    // Convert to respective data type
                    String name = nameCell.toString();
                    String location = locCell.toString();
                    int staffQuota = (int) quotaCell.getNumericCellValue();

                    // Add new branch
                    Branch branch = new Branch(name, location, staffQuota);
                    branchList.add(branch);

                    // Create a menu for each branch
                    menuList.add(new Menu(branch));
                }
            }
        }
    }

    // Initialise payment records
    public static ArrayList<Payment> initialisePaymentRecords() {
        ArrayList<Payment> paymentList = new ArrayList<Payment>();
        Payment card = new Payment("Credit/Debit Card");
        Payment online = new Payment("PayPal");
        paymentList.add(card);
        paymentList.add(online);
        return paymentList;
    }

    // Add menu items by branch
    public static void initialiseMenus(Company company) {
        XSSFSheet menuSheet = getSheet("./data/menu_list.xlsx");
        // Iterate all rows
        for (int i = 1; i < menuSheet.getPhysicalNumberOfRows(); i++) {
            Row row = menuSheet.getRow(i);
            if (row != null && row.getLastCellNum() >= 4) { // Ensure at least 4 cells in a row
                // Get values from rows
                Cell nameCell = row.getCell(0);
                Cell priceCell = row.getCell(1);
                Cell branchCell = row.getCell(2);
                Cell categoryCell = row.getCell(3);

                // Ensure cells are not empty
                if (nameCell != null && priceCell != null && branchCell != null && categoryCell != null) {
                    // Convert to respective data type
                    String name = nameCell.toString().trim();
                    double price = priceCell.getNumericCellValue();
                    String branch = branchCell.toString().trim();
                    String category = categoryCell.toString().trim();

                    // Add new item
                    company.getMenuDirectory().getMenu(branch).addItem(new MenuItem(name, price, branch, category));
                }
            }
        }
    }
}
