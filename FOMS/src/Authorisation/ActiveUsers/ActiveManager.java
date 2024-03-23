package Authorisation.ActiveUsers;

import Management.Company;
import Management.Manager;
import Management.Staff.Roles;

import java.util.Scanner;

import static Authentication.Authentication.changePassword;

public class ActiveManager implements ActiveUserInterface {
    private Manager activeStaff;

    public Manager getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Manager activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void processMenu(Scanner sc, Company company) {
        System.out.printf("\nStaffID: %s\tRole: %s\n", this.getActiveStaff().getStaffID(), Roles.MANAGER);
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Display Staff List\n2. Change Password\n3. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                this.getActiveStaff().displayStaffList(sc, company, Roles.MANAGER);
                break;
            case 2:
                changePassword(sc, this.getActiveStaff());
                break;
            case 3:
                this.logout();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }
}