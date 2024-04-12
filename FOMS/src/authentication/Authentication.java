package authentication;

import staff.StaffDirectory;
import utils.InputScanner;
import staff.Staff;

import static authentication.Hashing.genHash;

/**
 * Authenticate staff based on their roles
 */
public class Authentication {
    /**
     * Verify if the user entered the right password for the account
     *
     * @param staff Staff object based on the username entered
     * @param password Password inputted
     * @return Boolean value of whether the password matches
     */
    protected static boolean verifyPassword(Staff staff, String password) {
        return password.equals(staff.getPassword());
    }

    /**
     * Staff login into their account
     *
     * @param staffDirectory List of staff records
     * @return Nothing is returned
     */
    public static Staff login(StaffDirectory staffDirectory) {
        InputScanner sc = InputScanner.getInstance();
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

    /**
     * Change password
     *
     * @param staff Staff that wants to change their password
     */
    public static void setNewPassword(Staff staff) {
        InputScanner sc = InputScanner.getInstance();
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
