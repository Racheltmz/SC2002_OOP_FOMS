package staff;

import management.Company;
import utils.InputScanner;
import menu.MenuItem;
import menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static authorisation.Authorisation.authoriseManager;
import static branch.BranchDirectory.getBranchByUserInput;
import static utils.InputScanner.getInstance;
import static validation.ValidateDataType.*;

// TODO: STRUCT: MOVE PRINT STATEMENTS TO VIEW
// Manager's permissions
public class ManagerActions {
    // Add menu item
    public void addMenuItem(Company company, StaffRoles auth) throws InputMismatchException {
        if (authoriseManager(auth)) {
            validateMenuItemAddition(company);
        }
    }

    // Update menu item
    public void updateMenuItem(Company company, StaffRoles auth) {
        if (authoriseManager(auth)) {
            validateMenuItemUpdate(company);
        }
    }

    // Remove menu item
    public void removeMenuItem(Company company, StaffRoles auth) {
        if (authoriseManager(auth)) {
            validateMenuItemRemoval(company);
        }
    }
}
