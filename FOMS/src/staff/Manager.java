package staff;

import branch.Branch;
import utils.Filter;

import java.util.UUID;

import static authorisation.Authorisation.authoriseManager;

// Manager details
public class Manager extends Staff  {
    // Attributes
    private final ManagerActions managerActions = new ManagerActionsMenu();;

    // Constructor
    public Manager(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        super(staffID, name, role, gender, age, branch);
    }

    public Manager(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        super(id, staffID, name, role, gender, age, branch);
    }

    // Display staff list by branch
    public void displayStaffList(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)) {
            Filter staffFilterBranch = new StaffFilterBranch();
            staffFilterBranch.filter(branch.getName());
        }
    }

    // Add menu item
    public void addMenuItem(StaffRoles auth) {
        if (authoriseManager(auth)){
            managerActions.addMenuItem();
        }
    }

    // Update menu item
    public void updateMenuItem(StaffRoles auth) {
        if (authoriseManager(auth)){
            managerActions.updateMenuItem();
        }
    }

    // Remove menu item
    public void removeMenuItem(StaffRoles auth) {
        if (authoriseManager(auth)){
            managerActions.removeMenuItem();
        }
    }
}
