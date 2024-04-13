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
     * Staff login into their account
     *
     * @param staffDirectory List of staff records
     * @return Nothing is returned
     */
    public static Staff login(StaffDirectory staffDirectory) {
        InputScanner sc = InputScanner.getInstance();
        Staff staff = staffDirectory.getStaff();
        while (true) {
            System.out.print("Enter password: ");
            String inputPassword = sc.next();
            String securePassword = genHash(inputPassword);
            if (!staff.verifyPassword(securePassword)) {
                System.out.println("Incorrect password, please try again.");
            } else {
                System.out.println("Logged in!\n");
                return staff;
            }
        }
    }
}
