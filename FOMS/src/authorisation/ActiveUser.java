package authorisation;

import management.Company;
import staff.Staff;
import order.OrderQueue;

// Active user interface
public interface ActiveUser {
    // Get active staff
    Staff getActiveStaff();
    // Show options
    void showOptions(Company company, OrderQueue queue);
    // Logout of account
    void logout();
}
