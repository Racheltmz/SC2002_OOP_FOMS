package authorisation;

import exceptions.AuthorizationException;
import management.StaffRoles;

/**
 * Authorisation checks for secure access to functions.
 */
public class Authorisation {
    /**
     * Authorise users to use a certain function if they have a certain role.
     *
     * @param expectedRole Role needed to use a function.
     * @param roleToCheck Staff's current role.
     * @return Boolean value of whether the user is authorised or not.
     */
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

    /**
     * Check if role is a manager.
     *
     * @param role Active staff's role
     * @return Boolean value of whether the user is a manager or not.
    */
    public static boolean authoriseManager(StaffRoles role) {
        return authorise(StaffRoles.MANAGER, role);
    }

    /**
     * Check if role is an admin.
     *
     * @param role Active staff's role
     * @return Boolean value of whether the user is an admin or not.
     */
    public static boolean authoriseAdmin(StaffRoles role) {
        return authorise(StaffRoles.ADMIN, role);
    }
}
