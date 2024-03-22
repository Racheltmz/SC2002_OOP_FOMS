package Authentication.ActiveUsers;

import Management.Admin;
import Management.Company;

public class ActiveAdmin implements ActiveUserInterface {
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

    public void processMenu(Company company) {
        System.out.println("TBC");
    }
}