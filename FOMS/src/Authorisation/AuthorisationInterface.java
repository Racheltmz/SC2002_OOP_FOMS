package Authorisation;
import Management.Staff.Roles;

interface AuthorisationInterface {
    boolean authorise(Roles role);
}
