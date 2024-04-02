package authorisation;

import staff.Staff;

// Active factory for staff
public class ActiveFactoryStaff implements ActiveFactory {
    @Override
    public ActiveUser initInactive() {
        return new ActiveStaff();
    }
    @Override
    public ActiveUser initActive(Staff staff) {
        return new ActiveStaff(staff);
    }
}
