package management.actions;

import branch.Branch;
import menu.MenuDirectory;
import utils.InputScanner;
import menu.MenuItem;
import menu.Menu;
import java.util.InputMismatchException;

import static menu.MenuDirectory.getCategoryByUserInput;
import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.*;

// Manager's permissions
public class ManagerMenuActions implements IManagerMenuActions {
    InputScanner sc = InputScanner.getInstance();
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
            String category = getCategoryByUserInput();

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
        int size = branchMenu.getMenuItems().size();

        // Display items in branch
        branchMenu.displayItems();
        boolean success = false;

        // Get item to update by name
        int itemIndex = validateIntRange("Enter the index of the item you want to update: ", 1, size);
        MenuItem itemToUpdate = branchMenu.getMenuItems().get(itemIndex-1);

        while (!success) {
            int option = validateIntRange("1. Update name\n2. Update price\n3. Update category\n4. Update description\nSelect option (5 to quit): ", 1, 5);
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter new name of item: ");
                    String name = toProperCase(sc.next());
                    itemToUpdate.setName(name);
                    break;
                case 2:
                    double price = validateDouble("Enter price ($): ");
                    itemToUpdate.setPrice(price);
                    break;
                case 3:
                    String category = getCategoryByUserInput();
                    if (category != null) {
                        itemToUpdate.setCategory(category);
                    }
                    else {
                        System.out.println("Unable to update category.");
                    }
                    break;
                case 4:
                    System.out.println("Enter new description: ");
                    String description = sc.nextLine();
                    itemToUpdate.setDescription(description);
                    break;
                case 5:
                    success = true;
                    break;
            }
            branchMenu.updateItem(itemToUpdate);
        }
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