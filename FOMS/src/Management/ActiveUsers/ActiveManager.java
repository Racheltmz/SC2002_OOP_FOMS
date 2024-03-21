package Management.ActiveUsers;

import Authentication.Authentication;
import Management.Company;
import Management.Manager;

public class ActiveManager extends ActiveUser {
    private Manager activeStaff;

    public Manager getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Manager activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }

    public void processMenu(Company company, Authentication auth)
    {
        
    }
}