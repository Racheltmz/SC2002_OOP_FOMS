package staff;

import branch.Branch;
import branch.BranchDirectory;
import menu.MenuDirectory;
import utils.InputScanner;
import menu.MenuItem;
import menu.Menu;
import java.util.InputMismatchException;

import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.validateDouble;
import static utils.ValidateHelper.validateInt;

// Manager's permissions
public class ManagerActionsMenu implements IManagerActionsMenu {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();
    MenuDirectory menuDirectory = MenuDirectory.getInstance();

    // Add menu item
    public void addMenuItem(Branch branch) {
        try {
            String branchName = branch.getName();

            // Get details of new menu item
            System.out.println("Enter name: ");
            String name = toProperCase(sc.next());

            double price = validateDouble("Enter price ($): ");

            System.out.println("Enter category: ");
            String category = toProperCase(sc.next());

            System.out.println("Enter description: ");
            String description = sc.next();

            // Add the new menu item to the menu
            MenuItem newItem = new MenuItem(name, price, branchName, category, description);
            menuDirectory.getMenu(branchName).addItem(newItem, menuDirectory.getNumAllMenuItems(), false);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update menu item
    public void updateMenuItem(Branch branch) {
        // Get menu by branch
        String branchName = branch.getName();
        Menu branchMenu = menuDirectory.getMenu(branchName);
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
    public void removeMenuItem(Branch branch) {
        // Get menu by branch
        String branchName = branch.getName();
        Menu branchMenu = menuDirectory.getMenu(branchName);
        // Display items in branch
        branchMenu.displayItems();

        // Get item to remove by name
        int itemIndex = validateInt("Enter the number of the item you want to remove: ") - 1;
        MenuItem itemToRmv = branchMenu.getMenuItems().get(itemIndex);
        branchMenu.removeItem(itemToRmv);
    }
}
