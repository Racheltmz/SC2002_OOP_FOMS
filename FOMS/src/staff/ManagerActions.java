package staff;

import branch.BranchDirectory;
import menu.MenuDirectory;
import utils.InputScanner;
import menu.MenuItem;
import menu.Menu;
import java.util.InputMismatchException;

import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;

import static authorisation.Authorisation.authoriseManager;
import static branch.BranchDirectory.getBranchByUserInput;
import static validation.ValidateDataType.validateDouble;
import static validation.ValidateDataType.validateInt;

// TODO: SOLID: IMPLEMENT INTERFACE (For all 3 methods)
// TODO: STRUCT: MOVE PRINT STATEMENTS TO VIEW
// Manager's permissions
public class ManagerActions {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();
    MenuDirectory menuDirectory = MenuDirectory.getInstance();

    // Add menu item
    public void addMenuItem(StaffRoles auth) {
        if (authoriseManager(auth)) {
            try {
                // Get branch by user input
                String branch = getBranchByUserInput(branchDirectory);

                // Get details of new menu item
                System.out.println("Enter name: ");
                String name = sc.next();
                double price = validateDouble("Enter price ($): ");
                System.out.println("Enter category: ");
                String category = sc.next();
                System.out.println("Enter description: ");
                String description = sc.next();

                // Add the new menu item to the menu
                MenuItem newItem = new MenuItem(name, price, branch, category, description);
                menuDirectory.getMenu(branch).addItem(newItem, menuDirectory.getNumAllMenuItems(), false);
            } catch (InputMismatchException e) { //| MenuItemNotFoundException | ItemNotFoundException e
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Update menu item
    public void updateMenuItem(StaffRoles auth)
            throws MenuItemNotFoundException, ItemNotFoundException {
        if (authoriseManager(auth)) {
            // Get menu by branch
            String branch = getBranchByUserInput(branchDirectory);
            Menu branchMenu = menuDirectory.getMenu(branch);
            // Display items in branch
            branchMenu.displayItems();
            boolean success = false;
            do {
                try {
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
                } catch (InputMismatchException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (!success);
        }
    }

    // Remove menu item
    public void removeMenuItem(StaffRoles auth)
            throws IndexOutOfBoundsException {
        if (authoriseManager(auth)) {
            // Get menu by branch
            String branch = getBranchByUserInput(branchDirectory);
            Menu branchMenu = menuDirectory.getMenu(branch);
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
