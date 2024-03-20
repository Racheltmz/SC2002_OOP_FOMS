package Authentication;

import Management.Staff;

// Keep track of the staff that is logged in
public class ActiveStaff {
    private Staff activeStaff;

    public Staff getActiveStaff() {
        return activeStaff;
    }

    public void setActiveStaff(Staff activeStaff) {
        this.activeStaff = activeStaff;
    }

    public void logout() {
        setActiveStaff(null);
    }
}
