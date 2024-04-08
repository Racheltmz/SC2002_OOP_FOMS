package staff;

import management.Company;

import java.util.InputMismatchException;

import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;

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
        if (authoriseManager(auth)) {
            // Display staff info from branches
            String branch = getBranchByUserInput(company.getBranchDirectory());;
            displayStaffByBranch(company.getStaffDirectory().filterBranch(branch), branch);
        }
    }

    // Add menu item
    public void addMenuItem(Company company, StaffRoles auth) throws InputMismatchException, MenuItemNotFoundException, ItemNotFoundException {
        managerActions.addMenuItem(company, auth);
    }

    // Update menu item
    public void updateMenuItem(Company company, StaffRoles auth) throws MenuItemNotFoundException, ItemNotFoundException {
        managerActions.updateMenuItem(company, auth);
    }

    // Remove menu item
    public void removeMenuItem(Company company, StaffRoles auth) throws MenuItemNotFoundException, ItemNotFoundException {
        managerActions.removeMenuItem(company, auth);
    }

}
