package Authentication.ActiveUsers;

import Authentication.Authentication;
import Authentication.StaffPermissions;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;

public abstract class ActiveUser implements StaffPermissions {
    protected Staff activeStaff;
    protected Manager activeManager;
    protected Admin activeAdmin;

    @Override
    public void displayRoleInfo() 
    {

    }

    @Override
    public void logout() {
        // Implement logout method
    }

    @Override
    public void processMenu(Company company, Authentication auth) {

    }
}
