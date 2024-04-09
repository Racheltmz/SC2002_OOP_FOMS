package staff;

import management.Company;

import java.util.InputMismatchException;

import static authorisation.Authorisation.authoriseManager;
import static branch.BranchDirectory.getBranchByUserInput;
import static staff.StaffView.displayStaffByBranch;

// Manager details
public class Manager extends Staff  {
    // Attributes
    private ManagerActions managerActions;

    // Constructor
    public Manager(String staffID, String name, StaffRoles role, char gender, int age, String branch) {
        super(staffID, name, role, gender, age, branch);
        this.managerActions = new ManagerActions();
    }

    // Display staff list by branch
    public void displayStaffList(Company company, StaffRoles auth) {
        managerActions.displayStaffList(company, auth);
    }

    // Add menu item
    public void addMenuItem(Company company, StaffRoles auth) throws InputMismatchException {
        managerActions.addMenuItem(company, auth);
    }

    // Update menu item
    public void updateMenuItem(Company company, StaffRoles auth) {
        managerActions.updateMenuItem(company, auth);
    }

    // Remove menu item
    public void removeMenuItem(Company company, StaffRoles auth) {
        managerActions.removeMenuItem(company, auth);
    }

}
