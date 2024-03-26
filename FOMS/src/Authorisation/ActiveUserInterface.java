package Authorisation;

import Management.Company;
import Management.Staff;
import Order.OrderQueue;

public interface ActiveUserInterface {
    // Get active staff information
    Staff getActiveStaff();
    // Show options
    void showOptions(Company company, OrderQueue queue);
    // Logout of account
    void logout();
}
