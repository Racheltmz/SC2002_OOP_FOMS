package management;

import branch.Branch;
import management.actions.IManagerMenuActions;
import management.actions.IStaffFilterActions;
import management.actions.ManagerMenuActions;
import management.actions.StaffFilterActions;

import java.util.UUID;

import static authorisation.Authorisation.authoriseManager;

/**
 * Manager class represents an administrative user with more exclusive privileges than Staff.
 * This class extends the Staff class and provides methods for administrative actions to manage
 * Menus and Orders within their own Branch.
 */
public class Manager extends Staff {
    // Attributes
    private final IManagerMenuActions managerActions = new ManagerMenuActions();
    private final IStaffFilterActions filterActions = new StaffFilterActions();

    /**
     * Constructs a Manager using Staff constructor
     *
     * @param staffID Manager staffID
     * @param name Name of the Manager
     * @param role StaffRole of the Manager
     * @param gender Gender of the Manager
     * @param age Age of the Manager
     * @param branch Branch of the Manager
     */
    public Manager(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        super(staffID, name, role, gender, age, branch);
    }

    /**
     * Constructs a Manager using Staff constructor
     *
     * @param id Record ID
     * @param staffID Manager staffID
     * @param name Name of the Manager
     * @param role StaffRole of the Manager
     * @param gender Gender of the Manager
     * @param age Age of the Manager
     * @param branch Branch of the Manager
     * @param password Password of Manager
     */
    public Manager(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch, String password) {
        super(id, staffID, name, role, gender, age, branch, password);
    }

    /**
     * Displays list of staff in the Manager's Branch.
     *
     * @param auth the authorization role required (Manager).
     * @param branch the Branch of the Manager.
     */
    public void displayStaffList(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)) {
            filterActions.applyBranchFilter(branch.getName());
        }
    }

    /**
     * Adds a MenuItem to the Menu of the Manager's Branch.
     *
     * @param auth the authorization role required (Manager).
     * @param branch the Branch of the Manager.
     */
    public void addMenuItem(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)){
            managerActions.addMenuItem(branch);
        }
    }

    /**
     * Updates an existing MenuItem in the Menu of the Manager's Branch.
     *
     * @param auth the authorization role required (Manager).
     * @param branch the Branch of the Manager.
     */
    public void updateMenuItem(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)){
            managerActions.updateMenuItem(branch);
        }
    }

    /**
     * Removes an existing MenuItem in the Manager's Branch.
     *
     * @param auth the authorization role required (Manager).
     * @param branch the Branch of the Manager.
     */
    public void removeMenuItem(StaffRoles auth, Branch branch) {
        if (authoriseManager(auth)){
            managerActions.removeMenuItem(branch);
        }
    }
}