package Authentication.ActiveUsers;

import Management.Company;
import Management.Staff;

public interface ActiveUserInterface {
    // public void displayRoleInfo()
    public Staff getActiveStaff();
    public void logout();
    public void processMenu(Company company);
}
