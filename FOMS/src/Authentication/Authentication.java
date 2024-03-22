package Authentication;

import Management.Company;
import Management.Staff;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static Authentication.Hashing.genHash;

// Handles login and password
public class Authentication {
    public static Staff login(Company company) throws NoSuchAlgorithmException {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter staffID: ");
                String inputStaffID = sc.nextLine();
                // Get staff by id
                Staff staff = company.getStaff(inputStaffID);
                if (staff != null) {
                    System.out.print("Enter password: ");
                    String inputPassword = sc.nextLine().trim();
                    String securePassword = genHash(inputPassword);
                    if (!verifyPassword(staff, securePassword)) {
                        System.out.println("Incorrect password, please try again.");
                        break;
                    }
                    System.out.println("Logged in!");
                    return staff;
                } else {
                    System.out.println("Invalid staffID, please try again.");
                }
            }
        }
        return null;
    }

    protected static boolean verifyPassword(Staff staff, String password) {
        return password.equals(staff.getPassword());
    }

    public static void changePassword(Staff staff) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Please enter new password: ");
            String newpassword = sc.nextLine().trim();
            while (newpassword.isEmpty()) {
                System.out.println("Password change unsuccessful, please try again.");
                System.out.print("Please enter new password: ");
                newpassword = sc.nextLine().trim();
            }
            staff.setPassword(genHash(newpassword));
            System.out.println("Password successfully changed!");
        }
    }
}
