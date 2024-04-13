package authorisation;

import utils.InputScanner;
import staff.Admin;
import management.Company;
import order.OrderQueue;
import staff.AdminActions;

import static utils.InputScanner.getInstance;
import static validation.ValidateDataType.validateOption;

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

    public void showOptions(Company company, OrderQueue queue) {
        InputScanner sc = getInstance();
        System.out.println("-".repeat(30));
        System.out.printf("| StaffID: %s\n| Role: %s\n", getActiveStaff().getStaffID(),
                getActiveStaff().getRole());
        System.out.println("-".repeat(30));
        System.out.println("Please select option (3 to quit): ");
        int staffChoice = validateOption(("1. Change Password\n2. Update staff accounts\n3. Display staff list\n4. Assign manager to a branch" +
                "\n5. Promote staff to manager\n6. Transfer staff/manager to other branch\n7. Add payment method\n8. Remove payment method\n9. Open branch\n10. Close branch\n11. Logout\n"), 1, 11);
        switch (staffChoice) {
            case 1:
                this.getActiveStaff().changePassword(this.getActiveStaff());
                break;
            case 2:
                int choice = validateOption(("Please select option:\n1. Add staff account\n2. Edit staff account\n3. Remove staff account\n4. Return to main menu\n"), 1, 4);
                switch(choice) //TODO: IMPLEMENT STAFF ACCOUNT EDITING
                {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
                break;
            case 3:
                Admin.staffFiltering(company);
                break;
            case 4:
                System.out.println("Enter staffID of staff to assign as manager: ");
                String staffID = sc.next();
                System.out.println("Enter name of branch to assign manager to: ");
                String branchName = sc.next();
                branchName += sc.nextLine();
                Admin.assignManager(company, staffID, branchName);
                break;
            case 5:
                System.out.println("Enter staffID of staff to promote to manager: ");
                String staffId = sc.next();
                System.out.println("Enter name of branch to assign manager to: ");
                String branch = sc.next();
                branch += sc.nextLine();
                Admin.promoteStaff(company, staffId, branch);
                break;
            case 6:
                System.out.println("Enter staffID of staff to transfer: ");
                String staffid = sc.next();
                System.out.println("Enter name of branch to transfer staff to: ");
                String Branch = sc.next();
                Branch += sc.nextLine();
                Admin.transferStaff(company, staffid, Branch);
                break;
            case 7:
                System.out.print("Enter name of new payment method: ");
                String newMethod = sc.next();
                newMethod += sc.nextLine();
                AdminActions.addPayment(company, newMethod);
                System.out.println("Updated list of payment methods is: ");
                Admin.displayPaymentMethods(company);
                break;
            case 8:
                System.out.print("Enter name of payment method to remove: ");
                String methodToRemove = sc.next();
                methodToRemove += sc.nextLine();
                AdminActions.removePayment(company, methodToRemove);
                System.out.println("Updated list of payment methods is: ");
                Admin.displayPaymentMethods(company);
                break;
            case 9:
                Admin.addBranch(company);
                break;
            case 10:
                Admin.closeBranch(company);
                break;
            case 11:
                break;
        }
    }
}