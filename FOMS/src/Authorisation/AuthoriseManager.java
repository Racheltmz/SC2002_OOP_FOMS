package Authorisation;
import Management.Staff.Roles;

public class AuthoriseManager implements AuthorisationInterface {
    public boolean authorise(Roles role) {
        if (role == Roles.MANAGER) {
            return true;
        } else {
            System.out.println("Unauthorised access: this operation can only be accessed by an admin");
            return false;
        }
    }
}