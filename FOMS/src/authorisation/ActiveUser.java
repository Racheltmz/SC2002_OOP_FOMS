package authorisation;

import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;
import management.Company;
import staff.Staff;
import order.OrderQueue;

// Active user interface
public interface ActiveUser {
    // Get active staff
    Staff getActiveStaff();
    // Show options
    void showOptions(Company company, OrderQueue queue) throws MenuItemNotFoundException, ItemNotFoundException, EmptyListException;
    // Logout of account
    void logout();
}
