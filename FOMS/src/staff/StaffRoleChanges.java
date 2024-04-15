package staff;

import branch.Branch;


public class StaffRoleChanges {
    private StaffDirectory staffDirectory = StaffDirectory.getInstance(); 

    protected boolean assignManager(Branch branch, StaffRoles auth) {
        // Get number of managers
        int numManagers = staffDirectory.getNumManagersByBranch(branch, auth);
        // Check if the manager quota has exceeded
        if (numManagers < branch.getManagerQuota()) {
            System.out.print("Able to assign staff as a manager. The manager quota for " + branch.getLocation() + " has not been met.");
            return true;
        } else {
            System.out.print("Unable to assign staff as a manager. The manager quota for " + branch.getLocation() + " is already met.");
            return false;
        }
    }

    protected void promoteStaff(StaffRoles auth){
        StaffFilterActions filterActions = new StaffFilterActions(); 
        filterActions.applyRoleFilter(StaffRoles.STAFF.getAcronym()); 
        Staff staff = staffDirectory.getStaff();
        StaffRoles currentRole = staff.getRole();
        switch (currentRole) {
            case STAFF: 
                if (assignManager(staff.getBranch(), auth)) {
                    staffDirectory.upgradeCredentials(staff, auth);
                    System.out.print("Staff member with ID " + staff.getStaffID() + " has been promoted to Manager.");
                }
                break;
            case MANAGER:
                System.out.print("Manager cannot be promoted.");
                break;
            case ADMIN:
                System.out.println("Admin cannot be promoted.");
                break;
        }
    }
}
