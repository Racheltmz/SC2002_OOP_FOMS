package authorisation;

import utils.InputScanner;
import staff.Admin;

// Authorised operations for active user with an admin role
public class ActiveAdmin implements ActiveUser {
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
        switch (staffChoice) {
            case 1:
                // add staff
                this.getActiveStaff().addStaff(this.activeStaff.getRole());
                break;
            case 2:
                // edit staff
                this.getActiveStaff().updateStaff(this.activeStaff.getRole());
                break;
            case 3:
                // remove staff
                this.getActiveStaff().removeStaff(this.activeStaff.getRole());
                break;
            case 4:
//                this.getActiveStaff().setRole(this.activeStaff);
//                this.getActiveStaff().setBranch(this.activeStaff);
//                this.getActiveStaff().setStaffID(this.activeStaff);
//                this.getActiveStaff().filterStaff(this.activeStaff);
//                this.getActiveStaff().assignManager(this.activeStaff);
//                this.getActiveStaff().promoteStaff(this.activeStaff);
//                this.getActiveStaff().transferStaff(this.activeStaff);

//                this.getActiveStaff().addBranch(this.activeStaff);
//                this.getActiveStaff().closeBranch(this.activeStaff);

//                this.getActiveStaff().addPayment(this.activeStaff);
//                this.getActiveStaff().removePayment(this.activeStaff);

                this.getActiveStaff().changePassword(this.activeStaff);
                break;
            case 5:
                this.logout();
                break;
            default:
                System.out.println("Invalid choice, please re-enter: ");
                break;
        }
    }
}