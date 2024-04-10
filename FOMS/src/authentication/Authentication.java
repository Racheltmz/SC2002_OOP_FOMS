package authentication;

import staff.StaffDirectory;
import utils.InputScanner;
import staff.Staff;

import java.security.NoSuchAlgorithmException;

import exceptions.ItemNotFoundException;

import static authentication.Hashing.genHash;
import static utils.InputScanner.getInstance;

// TODO: STRUCT: CREATE A VIEW FOR THE PRINT STATEMENTS
// Authentication purposes (login)
public class Authentication {
    // Verify if the password matches
    protected static boolean verifyPassword(Staff staff, String password) {
        return password.equals(staff.getPassword());
    }

    // Login
    public static Staff login(StaffDirectory staffDirectory) throws NoSuchAlgorithmException, ItemNotFoundException {
        InputScanner sc = getInstance();
        while (true) {
            System.out.print("\nEnter staffID: ");
            String inputStaffID = sc.next();
            // Get staff by id
            Staff staff = staffDirectory.getStaff(inputStaffID);
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

    // Change password
    public static void setNewPassword(Staff staff) {
        InputScanner sc = getInstance();
        System.out.print("\nPlease enter new password: ");
        String newPassword = sc.next();
        // Check if user entered a password or if they entered the same password as they had in the past
        while (newPassword.isEmpty() || verifyPassword(staff, genHash(newPassword))) {
            System.out.println("Password change unsuccessful, please try again. Ensure that it differs from your previous password.");
            System.out.print("Please enter new password: ");
            newPassword = sc.next();
        }
        staff.setPassword(genHash(newPassword));
        System.out.println("Password successfully changed!");
    }
}
