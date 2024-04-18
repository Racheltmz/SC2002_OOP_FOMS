package authorisation;

import management.Staff;

/**
 * Factory interface for active staff.
 */
public interface ActiveFactory {
    /**
     * Initialise ActiveUser to inactive.
     * @return ActiveUser object.
     */
    IActiveUser initInactive();

    /**
     * Initialise ActiveUser to the current active staff.
     * @param staff Staff that has logged in.
     * @return ActiveUser object.
     */
    IActiveUser initActive(Staff staff);
}
