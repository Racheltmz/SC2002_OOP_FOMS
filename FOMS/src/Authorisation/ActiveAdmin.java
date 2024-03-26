package Authorisation;

import Initialisation.InputScanner;
import Management.Admin;
import Management.Company;
import Management.Staff.Roles;
import Order.OrderQueue;

import static Initialisation.InputScanner.getInstance;

public class ActiveAdmin implements ActiveUserInterface {
    private Admin activeStaff;

    public Admin getActiveStaff() {
        return this.activeStaff;
    }

    public void setActiveStaff(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void showOptions(Company company, OrderQueue queue) {
        InputScanner sc = getInstance();
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Change Password\n2. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                this.getActiveStaff().changePassword(this.getActiveStaff());
                break;
            case 2:
                this.logout();
                break;
            default:
                System.out.println("Invalid choice, please re-enter: ");
                break;
        }
    }
}