package Authentication.ActiveUsers;

import Authentication.StaffPermissions;
import Management.Admin;
import Management.Company;
import Management.Manager;
import Management.Staff;

public abstract class ActiveUser implements StaffPermissions {

    protected Staff activeStaff;

    @Override
    public void displayRoleInfo() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void processMenu(Company company) {

    }
}
