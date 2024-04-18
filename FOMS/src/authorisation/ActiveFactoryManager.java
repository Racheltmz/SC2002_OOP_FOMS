package authorisation;

import staff.Manager;
import staff.Staff;

/**
 * Factory for active manager.
 */
public class ActiveFactoryManager implements ActiveFactory {
    /**
     * Initialise ActiveManager to inactive.
     * @return ActiveManager object.
     */
    @Override
    public IActiveUser initInactive() {
        return new ActiveManager();
    }

    /**
     * Initialise ActiveManager to the current active staff.
     * @param staff Staff that has logged in.
     * @return ActiveManager object.
     */
    @Override
    public IActiveUser initActive(Staff staff) {
        return new ActiveManager((Manager) staff);
    }
}
