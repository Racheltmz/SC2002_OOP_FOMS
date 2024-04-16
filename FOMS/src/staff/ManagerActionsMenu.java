package staff;

import branch.BranchDirectory;
import menu.MenuDirectory;
import utils.InputScanner;
import menu.MenuItem;
import menu.Menu;
import java.util.InputMismatchException;

import static utils.ValidateHelper.*;

// Manager's permissions
public class ManagerActionsMenu implements ManagerActions {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();
    MenuDirectory menuDirectory = MenuDirectory.getInstance();

    // Add menu item
    public void addMenuItem() {
        try {
            // Get branch by user input
            String branch = branchDirectory.getBranchByUserInput().getName();

            // Get details of new menu item
            String name = validateString("Enter name: ");
            double price = validateDouble("Enter price ($): ");
            String category = MenuDirectory.getCategoryByUserInput();
            String description = validateString("Enter description: ");

            // Add the new menu item to the menu
            MenuItem newItem = new MenuItem(name, price, branch, category, description);
            menuDirectory.getMenu(branch).addItem(newItem, menuDirectory.getNumAllMenuItems(), false);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update menu item
    public void updateMenuItem() {
        // Get menu by branch
        String branch = branchDirectory.getBranchByUserInput().getName();
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
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid value, please enter again.");
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!success);
    }

    // Remove menu item
    public void removeMenuItem() {
        // Get menu by branch
        String branch = branchDirectory.getBranchByUserInput().getName();
        Menu branchMenu = menuDirectory.getMenu(branch);
        // Display items in branch
        branchMenu.displayItems();

        // Get item to remove by name
        int itemIndex = validateInt("Enter the number of the item you want to remove: ") - 1;
        MenuItem itemToRmv = branchMenu.getMenuItems().get(itemIndex);
        branchMenu.removeItem(itemToRmv);
    }
}
