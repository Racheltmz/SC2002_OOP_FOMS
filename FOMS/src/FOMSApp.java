// Import
<<<<<<< Updated upstream
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Authorisation.ActiveUsers.*;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;
import Management.Staff.Roles;
import Order.OrderQueue;

import static Authentication.Authentication.login;
import static Initialisation.Initialisation.initialiseCompany;

// Main app file
public class FOMSApp {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Initialise company and order queue
        Company company = initialiseCompany();
        OrderQueue orderQueue = new OrderQueue(10);

        /* FOMS INTERFACE */
        System.out.println("==================================");
        System.out.println("|         Welcome to FOMS!       |");
        System.out.println("|         By FDAB Group 3        |");
        System.out.println("==================================");
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Customer\n2. Staff");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                /* CUSTOMER INTERFACE */
                break;
            case 2:
                /* STAFF INTERFACE */
                ActiveStaff activeStaff = new ActiveStaff();
                ActiveManager activeManager = new ActiveManager();
                ActiveAdmin activeAdmin = new ActiveAdmin();
                Staff staff;

                // Iterate until user successfully logs in
                int staffChoice = 0;
                System.out.println("\nFOMS Login System.");
                sc.nextLine(); // Consume newline character

                do {
                    // Login
                    if (activeStaff.getActiveStaff() == null
                            && activeManager.getActiveStaff() == null
                            && activeAdmin.getActiveStaff() == null) {
                        System.out.println("\nPlease select option (2 to quit): ");
                        System.out.println("1. Login");
                        staffChoice = sc.nextInt();
                        sc.nextLine(); // Consume newline character
                        if (staffChoice == 1) {
                            try {
                                staff = login(sc, company);
                                // Set active staff
                                if (staff != null) {
                                    if (staff.getRole() == Roles.STAFF) {
                                        activeStaff.setActiveStaff(staff);
                                    } else if (staff.getRole() == Roles.MANAGER) {
                                        activeManager.setActiveStaff((Manager) staff);
                                    } else if (staff.getRole() == Roles.ADMIN) {
                                        activeAdmin.setActiveStaff((Admin) staff);
                                    }
                                }
                            }
                            catch (NoSuchAlgorithmException e) {
                                System.out.println("Unable to find algorithm.");
                            }
                        }
                    } else {
                        if (activeStaff.getActiveStaff() != null) {
                            activeStaff.processMenu(sc, company, orderQueue);
                        } else if (activeManager.getActiveStaff() != null) {
                            activeManager.processMenu(sc, company, orderQueue);
                        } else if (activeAdmin.getActiveStaff() != null) {
                            activeAdmin.processMenu(sc, company, orderQueue);
                        }
                    }
                } while (staffChoice != 2);
                break;
            case 3:
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}
=======
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.apache.poi.ss.usermodel.Row;

public class FOMSApp {
    public static void main(String[] args) throws IOException {
        // Try block to check for exceptions
        // try {

        //     // Reading file from local directory
        //     FileInputStream file = new FileInputStream(new File("./data/staff_list.xlsx"));

        //     // Create Workbook instance holding reference to
        //     // .xlsx file
        //     XSSFWorkbook workbook = new XSSFWorkbook(file);

        //     // Get first/desired sheet from the workbook
        //     XSSFSheet sheet = workbook.getSheetAt(0);

        //     // Iterate through each rows one by one
        //     Iterator<Row> rowIterator = sheet.iterator();

        //     // Initialise a list
        //     List<Staff> staffList = new ArrayList<Staff>();

        //     // Iterate all rows
        //     for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
        //         Row row = sheet.getRow(i);
        //         String staffId = row.getCell(1).toString();
        //         String name = row.getCell(0).toString();
        //         char role = row.getCell(2).toString().charAt(0);
        //         Staff.Roles roleCat = Staff.Roles.STAFF;
        //         switch (role) {
        //             case 'S':
        //                 roleCat = Staff.Roles.STAFF;
        //                 break;
        //             case 'M':
        //                 roleCat = Staff.Roles.MANAGER;
        //                 break;
        //             case 'A':
        //                 roleCat = Staff.Roles.ADMIN;
        //                 break;
        //         }
        //         char gender = row.getCell(3).toString().charAt(0);
        //         int age = (int) row.getCell(4).getNumericCellValue();
        //         String branch = row.getCell(5).toString();
        //         Staff staff = new Staff(staffId, name, roleCat, gender, age, branch);
        //         staff.getStaff();
        //         // Append new staff
        //         staffList.add(staff);
        //     }
        //     // Closing file output streams
        //     file.close();
        // }

        // // Catch block to handle exceptions
        // catch (NullPointerException nullPointerException) {
        //     System.out.println("Record was not inserted as it has an empty cell.");
        // }
        // catch (Exception e) {
            // Display the exception along with line number
            // using printStackTrace() method
            // e.printStackTrace();
        // }
    }
}
>>>>>>> Stashed changes
