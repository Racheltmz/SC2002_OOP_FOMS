package authorisation;

import exceptions.ExcelFileNotFound;
import staff.Admin;
import utils.InputScanner;

// Authorised operations for active user with an admin role
public class ActiveAdmin implements IActiveUser {
    private Admin activeStaff;

    public ActiveAdmin() { this.activeStaff = null; }
    
    public ActiveAdmin(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    public Admin getActiveStaff() {
        return this.activeStaff;
    }

    public void setActiveStaff(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void showOptions() {
        InputScanner sc = InputScanner.getInstance();
        System.out.println("-".repeat(30));
        System.out.printf("| StaffID: %s\n| Role: %s\n", getActiveStaff().getStaffID(),
                getActiveStaff().getRole());
        System.out.println("-".repeat(30));
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Change Password\n2. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine();
        // TODO: AFREEN: can we make sub switch statements so we further divide the functionality
        switch (staffChoice) {
            case 1:
                // add staff
                try {
                    this.getActiveStaff().addStaff(this.activeStaff.getRole());
                } catch (ExcelFileNotFound e) {
                    System.out.println("Error: Unable to add staff");
                }
                break;
            case 2:
                // edit staff
                try {
                    this.getActiveStaff().updateStaff(this.activeStaff.getRole());
                } catch (ExcelFileNotFound e) {
                    System.out.println("Error: Unable to update Staff details ");
                }
                break;
            case 3:
                // remove staff
                try {
                    this.getActiveStaff().removeStaff(this.activeStaff.getRole());
                } catch (ExcelFileNotFound e) {
                    System.out.println("Error: Unable to remove staff");
                }
                break;
            case 4:
                this.getActiveStaff().filterStaff(this.activeStaff.getRole());
                break;
            case 5:
                this.getActiveStaff().addBranch(this.activeStaff.getRole());
                break;
            case 6:
                this.getActiveStaff().closeBranch(this.activeStaff.getRole());
                break;
            case 7:
                try {
                    this.getActiveStaff().promoteStaff(this.activeStaff.getRole());
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 8:
                try {
                    this.getActiveStaff().transferStaff(this.activeStaff.getRole());
                } catch (ExcelFileNotFound e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 9:
//                this.getActiveStaff().addPayment(this.activeStaff.getRole());
                break;
            case 10:
//                this.getActiveStaff().removePayment(this.activeStaff.getRole());
                break;
            case 11:
                try {
                    this.getActiveStaff().changePassword();
                } catch (ExcelFileNotFound e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 12:
                this.logout();
                break;
            default:
                System.out.println("Invalid choice, please re-enter: ");
                break;
        }
    }
}