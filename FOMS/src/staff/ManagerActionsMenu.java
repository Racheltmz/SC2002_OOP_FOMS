package staff;

import static utils.ValidateHelper.*;

import java.util.InputMismatchException;

import branch.BranchDirectory;
import exceptions.ExcelFileNotFound;
import menu.Menu;
import menu.MenuDirectory;
import menu.MenuItem;
import utils.InputScanner;

// Manager's permissions
public class ManagerActionsMenu implements IManagerActionsMenu {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory;
    MenuDirectory menuDirectory;

    public ManagerActionsMenu() throws Exception {
        try {
            branchDirectory = BranchDirectory.getInstance();
            menuDirectory = MenuDirectory.getInstance();
        } catch (Exception e) {
            throw new Exception("Error initializing BranchDirectory or MenuDirectory: " + e.getMessage());
        }
    }

    // Add menu item
    public void addMenuItem() {
        try {
            // Get branch by user input
            String branch = branchDirectory.getBranchByUserInput().getName();

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
        } catch (InputMismatchException | ExcelFileNotFound e) {
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