package authorisation;

import staff.Staff;

// Active user interface
public interface IActiveUser {
    // Get active staff
    Staff getActiveStaff();
    // Show options
    void showOptions();
    // Logout of account
    void logout();
}
