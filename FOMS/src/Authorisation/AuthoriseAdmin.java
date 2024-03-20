public class AuthoriseAdmin implements AuthorisationInterface {
    public boolean authorise(Staff.Roles role) {
        if (role == Staff.Roles.ADMIN) {
            return true;
        } else {
            System.out.println("Unauthorised access: this operation can only be accessed by an admin");
            return false;
        }
    }
}
