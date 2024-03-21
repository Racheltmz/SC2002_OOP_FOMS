package Authentication;

import Management.Company;
import Management.Staff;

import java.util.Scanner;

// Handles login and password
public class Authentication {
    public Staff login(Company company) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter staffID: ");
            String inputStaffID = sc.next();
            // Get staff by id
            Staff staff = company.getStaff(inputStaffID);
            if (staff != null) 
            {
                System.out.print("Enter password: ");
                String inputPassword = sc.next();
                if (!verifyPassword(staff, inputPassword)) 
                {
                    System.out.println("Incorrect password, please try again.");
                    break;
                }
                System.out.println("Logged in!");
                sc.close();
                return staff;
            } 
            else 
            {
                System.out.println("Invalid staffID, please try again.");
            }
        }
        sc.close();
        return null;
    }

    protected boolean verifyPassword(Staff staff, String password) {
        return password.equals(staff.getPassword());
    }

    public void changePassword(Staff staff) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter new password: ");
        String newpassword = sc.next();
        while (newpassword.isEmpty()) 
        {
            System.out.println("Password change unsuccessful, please try again.");
            System.out.print("Please enter new password: ");
            newpassword = sc.next();
            sc.close();
        }
        staff.setPassword(newpassword);
        System.out.println("Password successfully changed!");
        sc.close();
    }
}