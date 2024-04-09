package authorisation;

import staff.StaffRoles;

// Authorise based on staff's role
public class Authorisation {
    // Functionalities
    private static boolean authorise(StaffRoles expectedRole, StaffRoles roleToCheck) {
        if (roleToCheck == expectedRole) {
            return true;
        } else {
            System.out.println("Unauthorised access: this operation can only be accessed by an admin");
            return false;
        }
    }
    public static boolean authoriseManager(StaffRoles role) {
        return authorise(StaffRoles.MANAGER, role);
    }

    public static boolean authoriseAdmin(StaffRoles role) {
        return authorise(StaffRoles.ADMIN, role);
    }
}
