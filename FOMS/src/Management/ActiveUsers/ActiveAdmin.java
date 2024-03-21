package Management.ActiveUsers;

import Authentication.Authentication;
import Management.Admin;
import Management.Company;

public class ActiveAdmin extends ActiveUser {
    private Admin activeStaff;

    public Admin getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Admin activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void processMenu(Company company, Authentication auth)
    {
        
    }
}