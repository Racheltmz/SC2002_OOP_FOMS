package Authentication;

import Management.Company;
import Management.Staff;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static Authentication.Hashing.genHash;

// Handles login and password
public class Authentication {

    // Verify if the password matches
    protected static boolean verifyPassword(Staff staff, String password) {
        return password.equals(staff.getPassword());
    }

    // Login for Test Case 9, 11, 24
    public static Staff login(Scanner sc, Company company) throws NoSuchAlgorithmException {
        while (true) {
            System.out.print("Enter staffID: ");
            String inputStaffID = sc.nextLine();
            // Get staff by id
            Staff staff = company.getStaff(inputStaffID);
            if (staff != null) {
                while (true) {
                    System.out.print("Enter password: ");
                    String inputPassword = sc.nextLine().trim();
                    String securePassword = genHash(inputPassword);
                    if (!verifyPassword(staff, securePassword)) {
                        System.out.println("Incorrect password, please try again.");
                    } else {
                        System.out.println("Logged in!");
                        return staff;
                    }
                }
            } else {
                System.out.println("Invalid staffID, please try again.");
            }
        }
    }

    // Change password for Test Case 25
    public static void changePassword(Scanner sc, Staff staff) {
        System.out.print("Please enter new password: ");
        String newpassword = sc.nextLine().trim();
        // Check if user entered a password or if they entered the same password as they had in the past
        while (newpassword.isEmpty() || verifyPassword(staff, genHash(newpassword))) {
            System.out.println("Password change unsuccessful, please try again. Ensure that it differs from your previous password.");
            System.out.print("Please enter new password: ");
            newpassword = sc.nextLine().trim();
        }
        staff.setPassword(genHash(newpassword));
        System.out.println("Password successfully changed!");
    }
}
