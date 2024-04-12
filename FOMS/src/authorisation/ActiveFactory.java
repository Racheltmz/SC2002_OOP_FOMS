package authorisation;

import staff.Staff;

// Active Factory interface
public interface ActiveFactory {
    /**
     * Initialise ActiveUser to inactive
     * @return ActiveUser object
     */
    ActiveUser initInactive();

    /**
     * Initialise ActiveUser to the current active staff
     * @param staff Staff that has logged in
     * @return ActiveUser object
     */
    ActiveUser initActive(Staff staff);
}
