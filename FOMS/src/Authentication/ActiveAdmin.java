package Authentication;

import Management.Admin;

public class ActiveAdmin {
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
}
