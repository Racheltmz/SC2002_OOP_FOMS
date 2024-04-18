package authorisation;

import management.Staff;

/**
 * Factory for active staff.
 */
public class ActiveFactoryStaff implements ActiveFactory {
    /**
     * Initialise ActiveStaff to inactive.
     * @return ActiveStaff object.
     */
    @Override
    public IActiveUser initInactive() {
        return new ActiveStaff();
    }

    /**
     * Initialise ActiveStaff to the current active staff.
     * @param staff Staff that has logged in.
     * @return ActiveStaff object.
     */
    @Override
    public IActiveUser initActive(Staff staff) {
        return new ActiveStaff(staff);
    }
}
