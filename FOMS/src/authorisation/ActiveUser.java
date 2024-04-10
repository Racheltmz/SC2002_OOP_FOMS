package authorisation;

import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;
import staff.Staff;
import order.OrderQueue;

// Active user interface
public interface ActiveUser {
    // Get active staff
    Staff getActiveStaff();
    // Show options
    void showOptions() throws MenuItemNotFoundException, ItemNotFoundException, EmptyListException;
    // Logout of account
    void logout();
}
