// Import
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Authentication.Authentication;
import Authentication.ActiveStaff;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class FOMSApp {
    public static void main(String[] args) throws IOException {
        // Initialise company
        Company company;
        // Initialise a list
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        /* LOAD STAFF LIST ON INITIALISATION */
        try {
            // Reading file from local directory
            FileInputStream file = new FileInputStream(new File("./data/staff_list.xlsx"));

            // Create Workbook instance holding reference to
            // .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

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
                Staff.Roles roleCat = null;

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
            // Closing workbook and file output streams
            workbook.close();
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

        // Create company
        company = new Company(staffList);

        /* FOMS INTERFACE */
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Customer\n2. Staff");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                /* CUSTOMER INTERFACE */
                break;
            case 2:
                /* STAFF INTERFACE */
                Authentication auth = new Authentication();
                ActiveStaff activeStaff = new ActiveStaff();
                Staff staff;

                // Iterate until user successfully logs in
                int staffChoice = 0;
                System.out.println("\nWelcome to the FOMS Login System.");
                sc.nextLine(); // Consume newline character

                do {
                    // Login
                    if (activeStaff.getActiveStaff() == null) {
                        System.out.println("\nPlease select option (2 to quit): ");
                        System.out.println("1. Login");
                        staffChoice = sc.nextInt();
                        sc.nextLine(); // Consume newline character
                        if (staffChoice == 1) {
                            staff = auth.login(sc, company);
                            // Set active staff
                            if (staff != null)
                                activeStaff.setActiveStaff(staff);
                        } else {
                            System.exit(0);
                        }
                    } else {
                        // Display options based on the active staff's role
                        Staff.Roles activeRole = activeStaff.getActiveStaff().getRole();
                        System.out.printf("\nStaffID: %s\tRole: %s\n", activeStaff.getActiveStaff().getStaffID(), activeRole);

                        switch (activeRole) {
                            // TODO: EDIT CHOICES AND IMPLEMENTATION (too much repetition)
                            case STAFF:
                                System.out.println("Please select option (3 to quit): ");
                                System.out.println("1. Change Password\n2. Logout");
                                staffChoice = sc.nextInt();
                                sc.nextLine(); // Consume newline character
                                switch (staffChoice) {
                                    case 1:
                                        if (activeStaff.getActiveStaff() != null)
                                            auth.changePassword(sc, activeStaff.getActiveStaff());
                                        else
                                            System.out.println("Please login first.");
                                        break;
                                    case 2:
                                        activeStaff.logout();
                                        break;
                                    default:
                                        System.out.print("Invalid choice, please re-enter: ");
                                        break;
                                }
                                break;
                            case MANAGER:
                                System.out.println("Please select option (3 to quit): ");
                                System.out.println("1. Change Password\n2. Logout");
                                staffChoice = sc.nextInt();
                                sc.nextLine(); // Consume newline character
                                switch (staffChoice) {
                                    case 1:
                                        if (activeStaff.getActiveStaff() != null)
                                            auth.changePassword(sc, activeStaff.getActiveStaff());
                                        else
                                            System.out.println("Please login first.");
                                        break;
                                    case 2:
//                                        activeStaff.displayStaffList();
//                                        break;
//                                        case 3:
                                        activeStaff.logout();
                                        break;
                                    default:
                                        System.out.print("Invalid choice, please re-enter: ");
                                        break;
                                }
                                break;
                            case ADMIN:
                                System.out.println("Please select option (3 to quit): ");
                                System.out.println("1. Change Password\n2. Logout");
                                staffChoice = sc.nextInt();
                                sc.nextLine(); // Consume newline character
                                switch (staffChoice) {
                                    case 1:
                                        if (activeStaff.getActiveStaff() != null)
                                            auth.changePassword(sc, activeStaff.getActiveStaff());
                                        else
                                            System.out.println("Please login first.");
                                        break;
                                    case 2:
                                        activeStaff.logout();
                                        break;
                                    default:
                                        System.out.print("Invalid choice, please re-enter: ");
                                        break;
                                }
                                break;
                        }
                    }
                } while (staffChoice != -1);
                break;
            case 3:
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}

