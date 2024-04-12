package staff;

import branch.BranchDirectory;

import java.util.UUID;

import static authorisation.Authorisation.authoriseManager;
import static branch.BranchDirectory.getBranchByUserInput;
import static staff.StaffView.displayStaffByBranch;

// Manager details
public class Manager extends Staff  {
    // Attributes
    private final ManagerActions managerActions;

    // Constructor
    public Manager(UUID id, String staffID, String name, StaffRoles role, char gender, int age, String branch) {
        super(id, staffID, name, role, gender, age, branch);
        this.managerActions = new ManagerActions();
    }

    // Display staff list by branch
    public void displayStaffList(StaffRoles auth) {
        if (authoriseManager(auth)) {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            BranchDirectory branchDirectory = BranchDirectory.getInstance();
            // Display staff info from branches
            String branch = getBranchByUserInput(branchDirectory);;
            displayStaffByBranch(staffDirectory.filterBranch(branch), branch);
        }
    }

    // Add menu item
    public void addMenuItem(StaffRoles auth) {
        managerActions.addMenuItem(auth);
    }

    // Update menu item
    public void updateMenuItem(StaffRoles auth) {
        managerActions.updateMenuItem(auth);
    }

    // Remove menu item
    public void removeMenuItem(StaffRoles auth) {
        managerActions.removeMenuItem(auth);
    }

}
