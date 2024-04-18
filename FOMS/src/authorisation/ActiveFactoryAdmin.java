package authorisation;

import management.Admin;
import management.Staff;

/**
 * Factory for active admin.
 */
public class ActiveFactoryAdmin implements ActiveFactory {
    /**
     * Initialise ActiveAdmin to inactive.
     * @return ActiveAdmin object.
     */
    @Override
    public IActiveUser initInactive() {
        return new ActiveAdmin();
    }

    /**
     * Initialise ActiveAdmin to the current active staff.
     * @param staff Staff that has logged in.
     * @return ActiveAdmin object.
     */
    @Override
    public IActiveUser initActive(Staff staff) {
        return new ActiveAdmin((Admin) staff);
    }
}
