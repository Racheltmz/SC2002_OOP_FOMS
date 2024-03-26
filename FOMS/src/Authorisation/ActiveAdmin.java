package Authorisation;

import Management.Admin;
import Management.Company;
import Management.Staff.Roles;
import Order.OrderQueue;

import static Authentication.Authentication.changePassword;

import java.util.Scanner;

public class ActiveAdmin implements ActiveUserInterface {
    private Admin activeStaff;

    public Admin getActiveStaff() {
        return this.activeStaff;
    }

    public void setActiveStaff(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void showOptions(Scanner sc, Company company, OrderQueue queue) {
        System.out.printf("\nStaffID: %s\tRole: %s\n\n", this.getActiveStaff().getStaffID(), Roles.ADMIN);
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Change Password\n2. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                changePassword(sc, this.getActiveStaff());
                break;
            case 2:
                this.logout();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;
        }
    }

    public void logout() {
        setActiveStaff(null);
    }
}