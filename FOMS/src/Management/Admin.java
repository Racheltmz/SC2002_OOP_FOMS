package management;

import staff.Staff;
import staff.StaffRoles;

import java.lang.reflect.Field;


public class Admin extends Staff{
    private AdminActions adminActions;

    public Admin(String staffID, String name, StaffRoles role, char gender, int age, String branch){
        super(staffID, name, role, gender, age, branch); 
    }

    public void setRole(String staffID, String branch){
        String staffIDFromObject = this.getStaffID();
        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
            if(this.getBranch().equals(branch)){
                System.out.println("Role updated successfully.");
            }
            else{
                System.out.println("This staff member does not belong to this branch.");
            }
        }
        else{
            System.out.println("Staff member not found.");
        }
    }

    public void setBranch(String staffID, String branch){
        String staffIDFromObject = this.getStaffID();
        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
            if(this.getBranch().equals(branch)){
                System.out.println("Branch updated successfully");
            }
            else{
                System.out.println("This staff member does not belong to this branch");
            }
        }
        else{
            System.out.println("Staff member not found");
        }
    }

    public void setStaffID(String staffID, String newStaffID){
        String staffIDFromObject = this.getStaffID(); 
        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
            try{
                Field staffIDField = Staff.class.getDeclaredField("staffID"); 
                staffIDField.setAccessible(true);
                staffIDField.set(this, newStaffID); 
                System.out.println("Staff ID updated successfully");
            } 
            catch(NoSuchFieldException | IllegalAccessException e){
                System.out.println("Error updating staff ID: " + e.getMessage());
            }
        }
        else{
            System.out.println("Staff member not found");
        }
    }

    protected void addBranch(String Branch){ 
        adminActions.addBranch(Branch); 
    }

    protected void closeBranch(String Branch){
        adminActions.closeBranch(Branch); 
    }

    protected void assignManager(String staffId, String Branch){
        adminActions.assignManager(staffId, Branch);
    }

    protected void promoteStaff(String staffID, String Branch){
        adminActions.promoteStaff(staffID, Branch);
    }

    protected void transferStaff(String staffID, String Branch){
        adminActions.transferStaff(staffID, Branch);
    }

    protected String addPayment(String method){
        return adminActions.addPayment(method);
    } 

    protected String removePayment(String method){
        return adminActions.removePayment(method); 
    }
}


