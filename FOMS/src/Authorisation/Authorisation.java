package Authorisation;

import Management.Staff.Roles;

public class Authorisation {
    private static boolean authorise(Roles expectedRole, Roles roleToCheck) {
        if (roleToCheck == expectedRole) {
            return true;
        } else {
            System.out.println("Unauthorised access: this operation can only be accessed by an admin");
            return false;
        }
    }
    public static boolean authoriseManager(Roles role) {
        return authorise(Roles.MANAGER, role);
    }

    public static boolean authoriseAdmin(Roles role) {
        return authorise(Roles.ADMIN, role);
    }
}
