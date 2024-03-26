package Authentication;

import Initialisation.InputScanner;
import Management.Company;
import Management.Staff;

import java.security.NoSuchAlgorithmException;

import static Authentication.Hashing.genHash;
import static Initialisation.InputScanner.getInstance;

// Handles login and password
public class Authentication {

    // Verify if the password matches
    protected static boolean verifyPassword(Staff staff, String password) {
        return password.equals(staff.getPassword());
    }

    // Login for Test Case 9, 11, 24
    public static Staff login(Company company) throws NoSuchAlgorithmException {
        InputScanner sc = getInstance();
        while (true) {
            System.out.print("\nEnter staffID: ");
            String inputStaffID = sc.next();
            // Get staff by id
            Staff staff = company.getStaff(inputStaffID);
            if (staff != null) {
                while (true) {
                    System.out.print("Enter password: ");
                    String inputPassword = sc.next();
                    String securePassword = genHash(inputPassword);
                    if (!verifyPassword(staff, securePassword)) {
                        System.out.println("Incorrect password, please try again.");
                    } else {
                        System.out.println("Logged in!\n");
                        return staff;
                    }
                }
            } else {
                System.out.println("Invalid staffID, please try again.");
            }
        }
    }

    // Change password for Test Case 25
    public static void setNewPassword(Staff staff) {
        InputScanner sc = getInstance();
        System.out.print("\nPlease enter new password: ");
        String setNewPassword = sc.next();
        // Check if user entered a password or if they entered the same password as they had in the past
        while (setNewPassword.isEmpty() || verifyPassword(staff, genHash(setNewPassword))) {
            System.out.println("Password change unsuccessful, please try again. Ensure that it differs from your previous password.");
            System.out.print("Please enter new password: ");
            setNewPassword = sc.next();
        }
        staff.setPassword(genHash(setNewPassword));
        System.out.println("Password successfully changed!");
    }
}
