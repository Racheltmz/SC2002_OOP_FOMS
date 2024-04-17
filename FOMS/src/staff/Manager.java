package staff;

import static authorisation.Authorisation.*;

import java.util.UUID;

import branch.Branch;
import exceptions.ExcelFileNotFound;
import staff.filter.StaffFilterBranch;
import utils.IFilter;

// Manager details
public class Manager extends Staff {
    // Attributes
    private final IManagerActionsMenu managerActions;

    // Constructor
    public Manager(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) throws Exception {
        super(staffID, name, role, gender, age, branch);
        managerActions = new ManagerActionsMenu();

    }

    public Manager(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch, String password) throws Exception {
        super(id, staffID, name, role, gender, age, branch, password);
        try{
            managerActions = new ManagerActionsMenu();
        }catch(ExcelFileNotFound e){
            throw new ExcelFileNotFound("Error initialising ManaagerActions: " + e.getMessage());
        }

    }

    // Display staff list by branch
    public void displayStaffList(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)) {
            IFilter staffFilterBranch = new StaffFilterBranch();
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
