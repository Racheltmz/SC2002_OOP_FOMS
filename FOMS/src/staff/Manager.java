package staff;

import branch.Branch;

import java.util.UUID;

import static authorisation.Authorisation.authoriseManager;

// Manager details
public class Manager extends Staff  {
    // Attributes
    private final ManagerView managerView = new ManagerView();;
    private final ManagerActions managerActions;

    // Constructor
    public Manager(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        super(staffID, name, role, gender, age, branch);
        this.managerActions = new ManagerActionsMenu();
    }

    public Manager(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        super(id, staffID, name, role, gender, age, branch);
        this.managerActions = new ManagerActionsMenu();
    }

    // Display staff list by branch
    public void displayStaffList(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)) {
            managerView.displayStaffByBranch(branch);
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
