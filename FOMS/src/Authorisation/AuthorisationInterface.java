package Authorisation;
import Management.Staff;

interface AuthorisationInterface {
    public boolean authorise(Staff.Roles role);
}
