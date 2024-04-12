package authorisation;

import exceptions.AuthorizationException;
import staff.StaffRoles;

// Authorise based on staff's role
public class Authorisation {
    // Functionalities
    private static boolean authorise(StaffRoles expectedRole, StaffRoles roleToCheck) {
        try {
            if (roleToCheck == expectedRole) {
                return true;
            } else {
                throw new AuthorizationException("Unauthorized access! Please login with the correct credentials.");
            }
        } catch (AuthorizationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean authoriseManager(StaffRoles role) {
        return authorise(StaffRoles.MANAGER, role);
    }

    public static boolean authoriseAdmin(StaffRoles role) {
        return authorise(StaffRoles.ADMIN, role);
    }
}
