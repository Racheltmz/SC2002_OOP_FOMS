package authorisation;

import payment.PaymentDirectory;
import utils.InputScanner;
import staff.Admin;

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
        System.out.println("Please select option (12 to quit): ");
        System.out.println("1. Add staff account\n2. Update staff details\n3. Remove staff account\n4. Filter staff\n5. Open branch\n6. Close branch\n" +
                "7. Promote staff\n8. Transfer staff\n9. Add new payment method\n10. Remove payment method\n11. Change password\n12. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine();
        // TODO: AFREEN: can we make sub switch statements so we further divide the functionality
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
                this.getActiveStaff().filterStaff(this.activeStaff.getRole());
                break;
            case 5:
                this.getActiveStaff().addBranch(this.activeStaff.getRole());
                break;
            case 6:
                this.getActiveStaff().closeBranch(this.activeStaff.getRole());
                break;
            case 7:
                this.getActiveStaff().promoteStaff(this.activeStaff.getRole());
                break;
            case 8:
                this.getActiveStaff().transferStaff(this.activeStaff.getRole());
                break;
            case 9:
                PaymentDirectory.addPaymentMtd();
                break;
            case 10:
                PaymentDirectory.removePaymentMtd();
                break;
            case 11:
                this.getActiveStaff().changePassword();
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