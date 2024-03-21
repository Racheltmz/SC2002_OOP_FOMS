package Authorisation;
import Management.Staff.Roles;

public class AuthoriseAdmin implements AuthorisationInterface {
    public boolean authorise(Roles role) {
        if (role == Roles.ADMIN) {
            return true;
        } else {
            System.out.println("Unauthorised access: this operation can only be accessed by an admin");
            return false;
        }
    }
}