package Authentication.ActiveUsers;

import java.util.Scanner;

import Authentication.Authentication;
import Management.Company;
import Management.Staff;
import Management.Staff.Roles;

// Keep track of the staff that is logged in
public class ActiveStaff extends ActiveUser {
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

    public void processMenu(Company company, Authentication auth)
    {
        System.out.printf("\nStaffID: %s\tRole: %s\n", this.getActiveStaff().getStaffID(), Roles.STAFF);
        Scanner sc = new Scanner(System.in);            
        System.out.println("Please select option (3 to quit): ");
        System.out.println("1. Change Password\n2. Logout");
        int staffChoice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (staffChoice) {
            case 1:
                if (this.getActiveStaff() != null)
                {
                    auth.changePassword(this.getActiveStaff());
                }
                else
                {
                    System.out.println("Please login first.");
                }
                sc.close();
                break;
            case 2:
                this.logout();
                sc.close();
                break;
            default:
                System.out.print("Invalid choice, please re-enter: ");
                break;  
        }
    }
}