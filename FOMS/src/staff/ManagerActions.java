package staff;

import management.Company;
import utils.InputScanner;
import menu.MenuItem;
import menu.Menu;

import java.util.InputMismatchException;

import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;
import exceptions.StringIndexOutOfBoundsException;

import static authorisation.Authorisation.authoriseManager;
import static branch.BranchDirectory.getBranchByUserInput;
import static utils.InputScanner.getInstance;
import static validation.ValidateDataType.validateDouble;
import static validation.ValidateDataType.validateInt;

// TODO: SOLID: IMPLEMENT INTERFACE (For all 3 methods)
// TODO: STRUCT: MOVE PRINT STATEMENTS TO VIEW
// Manager's permissions
public class ManagerActions {
    // Add menu item
    public void addMenuItem(Company company, StaffRoles auth) throws InputMismatchException, MenuItemNotFoundException, ItemNotFoundException {
        if (authoriseManager(auth)) {
            InputScanner sc = getInstance();
            // Get menu by branch
            String branch = getBranchByUserInput(company.getBranchDirectory());
            // Get item details
            System.out.println("Enter name: ");
            String name = sc.next();
            double price = validateDouble("Enter price ($): ");
            System.out.println("Enter category: ");
            String category = sc.next();
            company.getMenuDirectory().getMenu(branch).addItem(new MenuItem(name, price, branch, category));
        }
    }

    // Update menu item
    public void updateMenuItem(Company company, StaffRoles auth) throws MenuItemNotFoundException, ItemNotFoundException,IndexOutOfBoundsException {
        if (authoriseManager(auth)) {
            InputScanner sc = getInstance();
            // Get menu by branch
            String branch = getBranchByUserInput(company.getBranchDirectory());
            Menu branchMenu = company.getMenuDirectory().getMenu(branch);
            // Display items in branch
            branchMenu.displayItems();
            boolean success = false;
            do {
                    // Get item to update by name
                    int itemIndex = validateInt("Enter the number of the item you want to update: ") - 1;
                    MenuItem itemToUpdate = branchMenu.getMenuItems().get(itemIndex);
                    // Update price and description
                    double price = validateDouble("Enter price ($): ");
                    System.out.println("Enter description: ");
                    String description = sc.next();
                    branchMenu.updateItem(itemToUpdate, price, description);
                    success = true;
                    System.out.println("Item successfully updated in menu!");
            } while (!success);
        }
    }

    // Remove menu item
    public void removeMenuItem(Company company, StaffRoles auth) throws MenuItemNotFoundException,IndexOutOfBoundsException, ItemNotFoundException {
        if (authoriseManager(auth)) {
            // Get menu by branch
            String branch = getBranchByUserInput(company.getBranchDirectory());
            Menu branchMenu = company.getMenuDirectory().getMenu(branch);
            // Display items in branch
            branchMenu.displayItems();

            boolean success = false;
            do {
                    // Get item to remove by name
                    int itemIndex = validateInt("Enter the number of the item you want to remove: ") - 1;
                    MenuItem itemToRmv = branchMenu.getMenuItems().get(itemIndex);
                    branchMenu.removeItem(itemToRmv);
                    success = true;
                    System.out.println("Item successfully removed from menu!");
            } while (!success);
        }
    }
}
