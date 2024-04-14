package authorisation;

import staff.Staff;

// Active factory for staff
public class ActiveFactoryStaff implements ActiveFactory {
    @Override
    public IActiveUser initInactive() {
        return new ActiveStaff();
    }
    @Override
    public IActiveUser initActive(Staff staff) {
        return new ActiveStaff(staff);
    }
}
