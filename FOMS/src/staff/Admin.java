package staff;

import static authorisation.Authorisation.*;

import java.util.UUID;

import exceptions.ExcelFileNotFound;
import staff.filter.StaffFilterOptions;

public class Admin extends Staff {
    private final AdminView adminView = new AdminView();
    private final AdminActions adminActions;

    public Admin(String staffID, String name, StaffRoles role, char gender, int age) throws Exception {
        super(staffID, name, role, gender, age, null);
        try {
            adminActions = new AdminActions();
        } catch (ExcelFileNotFound e) {
            throw new ExcelFileNotFound("Error initializing AdminActions: " + e.getMessage());
        }
    }

    /**
     * Constructor for admin
     *
     * @param id Record ID
     * @param staffID Admin staffID
     * @param name Name of admin
     * @param role Role
     * @param gender Gender of admin
     * @param age Age of admin
     * @param password Password of admin
     * @throws Exception 
     */
    public Admin(UUID id, String staffID, String name, StaffRoles role, char gender, int age, String password) throws Exception {
        super(id, staffID, name, role, gender, age, null, password);
        adminActions = new AdminActions(); // Assuming no file operation here, so no need for exception handling
    }

    // Other methods omitted for brevity


    // Add menu item
    public void addStaff(StaffRoles auth) throws ExcelFileNotFound {
        if (authoriseAdmin(auth)){
            adminActions.addStaff(auth);
        }
    }

    // Update menu item
    public void updateStaff(StaffRoles auth) throws ExcelFileNotFound {
        if (authoriseAdmin(auth)){
            adminActions.updateStaff(auth);
        }
    }

    // Remove menu item
    public void removeStaff(StaffRoles auth) throws ExcelFileNotFound {
        if (authoriseAdmin(auth)){
            adminActions.removeStaff(auth);
        }
    }

    /**
     * Filter staff records
     *
     * @param auth Admin role required
     */

    public void filterStaff(StaffRoles auth) {
        if (authoriseAdmin(auth)){
            // Get field to filter staff records
            StaffFilterOptions option = adminView.getFieldToFilter();
            adminActions.filterStaff(option);
        }
    }

    public void promoteStaff(StaffRoles auth) throws Exception{
        if (authoriseAdmin(auth)) {
            adminActions.promoteStaff(auth);
        }
    }

    public void transferStaff(StaffRoles auth) throws ExcelFileNotFound{
        if (authoriseAdmin(auth)) {
            adminActions.transferStaff(auth);
        }
    }

    public void addBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            adminActions.addBranch(auth);
        }
    }

    public void closeBranch(StaffRoles auth){
        if (authoriseAdmin(auth)) {
            try {
                adminActions.closeBranch(auth);
            } catch (ExcelFileNotFound e) {
                // TODO Auto-generated catch block
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
//
//    protected String addPayment(String method){
//        return adminActions.addPayment(method);
//    }
//
//    protected String removePayment(String method){
//        return adminActions.removePayment(method);
//    }
}


