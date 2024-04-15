package authorisation;

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
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Change Password\n2. Logout");
        int categoryChoice = sc.nextInt();
        sc.nextLine();
        // TODO: AFREEN: can we make sub switch statements so we further divide the functionality(done)
        switch(categoryChoice){
            case 1:
                System.out.print("1. Add Staff \n2. Update Staff\n3. Remove Staff");
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
                    default:
                        System.out.print("Invalid choice, please re-enter: ");
                        break; 
                }
                break; 
            case 2:
                System.out.print("1. Add branch\n2. Close Branch");
                int branchChoice = sc.nextInt(); 
                sc.nextLine();
                switch(branchChoice){
                    case 1:
                    this.getActiveStaff().addBranch(this.activeStaff.getRole());
                    break;
                case 2:
                    this.getActiveStaff().closeBranch(this.activeStaff.getRole());
                    break;
                default:
                    System.out.println("Invalid choice, please re-enter: ");
                    break;
            }
            break;
        case 3: // Staff Changes
            System.out.println("1. Promote Staff\n2. Transfer Staff");
            int changeChoice = sc.nextInt();
            sc.nextLine();
            switch (changeChoice) {
                case 1:
                    this.getActiveStaff().promoteStaff(this.activeStaff.getRole());
                    break;
                case 2:
                    this.getActiveStaff().transferStaff(this.activeStaff.getRole());
                    break;
                default:
                    System.out.println("Invalid choice, please re-enter: ");
                    break;
            }
            break;
        case 4: // Miscellaneous
            System.out.println("1. Change Password\n2. Logout");
            int miscChoice = sc.nextInt();
            sc.nextLine();
            switch (miscChoice) {
                case 1:
                    this.getActiveStaff().changePassword();
                    break;
                case 2:
                    this.logout();
                    break;
                default:
                    System.out.println("Invalid choice, please re-enter: ");
                    break;
            }
            break;
        case 5: // Quit
            System.out.println("Exiting...");
            break;
        default:
            System.out.println("Invalid choice, please re-enter: ");
            break;
        }
    }
}