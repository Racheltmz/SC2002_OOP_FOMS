package staff;

import branch.BranchDirectory;
import menu.MenuDirectory;
import utils.InputScanner;
import utils.MenuItemXlsxHelper;
import menu.MenuItem;
import menu.Menu;
import java.io.IOException;
import java.util.ArrayList;
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

    MenuItemXlsxHelper menuItemXlsxHelper = new MenuItemXlsxHelper();

    // Add menu item
    public void addMenuItem(StaffRoles auth) {
        if (authoriseManager(auth)) {
            InputScanner sc = InputScanner.getInstance();
            BranchDirectory branchDirectory = BranchDirectory.getInstance();
            MenuDirectory menuDirectory = MenuDirectory.getInstance();
            try {
                // Get branch by user input
                String branch = getBranchByUserInput(branchDirectory);

                // Get details of new menu item
                System.out.println("Enter name: ");
                String name = sc.next();
                double price = validateDouble("Enter price ($): ");
                System.out.println("Enter category: ");
                String category = sc.next();

                // Add the new menu item to the menu
                MenuItem newItem = new MenuItem(name, price, branch, category);
                menuDirectory.getMenu(branch).addItem(newItem);

                // Write the updated menu items to the Excel file
                menuItemXlsxHelper.writeToXlsx(menuDirectory.getNumAllMenuItems(), newItem);
                System.out.println("New menu item added and saved to menu_list.xlsx.");

            } catch (IOException e) {
                System.out.println("Failed to save menu items to menu_list.xlsx: " + e.getMessage());
            } catch (InputMismatchException e) { //| MenuItemNotFoundException | ItemNotFoundException e
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Update menu item
    public void updateMenuItem(StaffRoles auth)
            throws MenuItemNotFoundException, ItemNotFoundException {
        if (authoriseManager(auth)) {
            InputScanner sc = InputScanner.getInstance();
            BranchDirectory branchDirectory = BranchDirectory.getInstance();
            MenuDirectory menuDirectory = MenuDirectory.getInstance();

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

                    // Read existing menu items from the Excel file
                    ArrayList<MenuItem> menuItems = menuItemXlsxHelper.readFromXlsx();

                    // Find and update the item in the list
                    for (MenuItem menuItem : menuItems) {
                        if (menuItem.getName().equals(itemToUpdate.getName())) {
                            menuItem.setPrice(price);
                            // Update description if needed
                            // menuItem.setDescription(description);
                            break;
                        }
                    }

                    // Write the updated menu items to the Excel file
//                    menuItemXlsxHelper.writeToXlsx(menuItems);
                    System.out.println("Menu items updated and saved to menu_list.xlsx.");
                } catch (IOException e) {
                    System.out.println("Failed to save menu items to menu_list.xlsx: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (!success);
        }
    }

    // Remove menu item
    public void removeMenuItem(StaffRoles auth)
            throws MenuItemNotFoundException, IndexOutOfBoundsException, ItemNotFoundException {
        if (authoriseManager(auth)) {
            BranchDirectory branchDirectory = BranchDirectory.getInstance();
            MenuDirectory menuDirectory = MenuDirectory.getInstance();

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
