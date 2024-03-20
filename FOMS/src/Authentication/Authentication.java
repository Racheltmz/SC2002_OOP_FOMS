package Authentication;

import Management.Company;
import Management.Staff;

import java.util.Scanner;
// Handles login and password
public class Authentication {
    public Staff login(Scanner sc, Company company) {
        boolean isLoggedIn = false;
        Staff staff;
        while (true) {
            System.out.print("Enter staffID: ");
            String inputStaffID = sc.nextLine();
            // Get staff by id
            staff = company.getStaff(inputStaffID);
            if (staff != null) {
                System.out.print("Enter password: ");
                String inputPassword = sc.nextLine().trim();
                if (!verifyPassword(staff, inputPassword)) {
                    System.out.println("Incorrect password, please try again.");
                    break;
                };
                isLoggedIn = true;
                System.out.println("Logged in!");
                return staff;
            } else {
                System.out.println("Invalid staffID, please try again.");
            }
        }
        return null;
    }

    protected boolean verifyPassword(Staff staff, String password) {
        return password.equals(staff.getPassword());
    }

    public void changePassword(Scanner sc, Staff staff) {
        System.out.print("Please enter new password: ");
        String newpassword = sc.nextLine().trim();
        while (newpassword.isEmpty()) {
            System.out.println("Password change unsuccessful, please try again.");
            System.out.print("Please enter new password: ");
            newpassword = sc.nextLine().trim();
        }
        staff.setPassword(newpassword);
        System.out.println("Password successfully changed!");
    }
}
