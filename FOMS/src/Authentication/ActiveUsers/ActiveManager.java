package Authentication.ActiveUsers;

import Management.Company;
import Management.Manager;

public class ActiveManager implements ActiveUserInterface {
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

    public void processMenu(Company company) {
        System.out.println("TBC");
    }
}