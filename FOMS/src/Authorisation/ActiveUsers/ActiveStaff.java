package Authorisation.ActiveUsers;

import java.util.Scanner;

import Management.Company;
import Management.Staff;
import Management.Staff.Roles;

import static Authentication.Authentication.changePassword;

// Keep track of the staff that is logged in
public class ActiveStaff implements ActiveUserInterface {
    private Staff activeStaff;

    public Staff getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Staff activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void processMenu(Scanner sc, Company company) {
        System.out.printf("\nStaffID: %s\tRole: %s\n", this.getActiveStaff().getStaffID(), Roles.STAFF);
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Change Password\n2. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                if (this.getActiveStaff() != null) {
                    changePassword(sc, this.getActiveStaff());
                } else {
                    System.out.println("Please login first.");
                }
                break;
            case 2:
                this.logout();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}