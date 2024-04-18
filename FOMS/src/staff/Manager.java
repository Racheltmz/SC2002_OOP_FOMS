package staff;

import branch.Branch;
import staff.filter.StaffFilterBranch;
import utils.IFilter;

import java.util.UUID;

import static authorisation.Authorisation.authoriseManager;

// Manager details
public class Manager extends Staff {
    // Attributes
    private final IManagerMenuActions managerActions = new ManagerMenuActions();
    StaffFilterActions filterActions = new StaffFilterActions();

    // Constructor
    public Manager(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        super(staffID, name, role, gender, age, branch);
    }

    public Manager(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch, String password) {
        super(id, staffID, name, role, gender, age, branch, password);
    }

    // Display staff list by branch
    public void displayStaffList(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)) {
            filterActions.applyBranchFilter(branch.getName());
        }
    }

    // Add menu item
    public void addMenuItem(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)){
            managerActions.addMenuItem(branch);
        }
    }

    // Update menu item
    public void updateMenuItem(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)){
            managerActions.updateMenuItem(branch);
        }
    }

    // Remove menu item
    public void removeMenuItem(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)){
            managerActions.removeMenuItem(branch);
        }
    }
}