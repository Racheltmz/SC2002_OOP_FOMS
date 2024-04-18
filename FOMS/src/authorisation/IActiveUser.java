package authorisation;

import staff.Staff;

/**
 * Active user interface.
  */
public interface IActiveUser {
    /**
     * Get active staff.
     * @return Active Staff object.
     */
    Staff getActiveStaff();

    /**
     * Display staff options.
     */
    void showOptions();

    /**
     * Logout of account.
     */
    void logout();
}
