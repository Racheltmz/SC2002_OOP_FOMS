package menu;

import java.util.ArrayList;

import branch.Branch;
import exceptions.DuplicateEntryException;
import exceptions.EmptyListException;
import exceptions.ExcelFileNotFound;
import io.MenuItemXlsxHelper;

// Menu details
public class Menu {
    // Attributes
    private Branch branch;
    private ArrayList<MenuItem> menuItems;

    // Constructor
    public Menu(Branch branch) {
        this.branch = branch;
        this.menuItems = new ArrayList<>();
    }

    // Getters
    public Branch getBranch() {
        return branch;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    // Functionalities
    // Display information
    public void displayItems() {
        System.out.printf("| No. | %-10s | %-20s | %-10s | %-50s |\n", "Category", "Name", "Price ($)", "Description");
        System.out.println("-".repeat(110));
        for (int i = 0; i < this.menuItems.size(); i++) {
            MenuItem item = this.menuItems.get(i);
            System.out.printf("| %-3d | %-10s | %-20s | %-10s | %-50s |\n", i + 1, item.getCategory(), item.getName(),
                    item.getPrice(), item.getDescription());
        }
    }

    public boolean itemsExist() {
        try {
            if (this.menuItems.isEmpty())
                throw new EmptyListException(
                        "There are no items on the menu. Please approach the staff for assistance.");
            else
                return true;
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean itemExistsByName(String name) {
        for (MenuItem item : this.menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Add menu item
    public void addItem(MenuItem menuItem, int numExistingItems, boolean stored) throws ExcelFileNotFound {
        try {
            if (!stored) {
                MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
                menuItemXlsxHelper.writeToXlsx(menuItem, numExistingItems);
                System.out.println("Item successfully added in menu!");
            }
            this.menuItems.add(menuItem);
        } catch (DuplicateEntryException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update menu item
    public void updateItem(MenuItem itemToUpdate, double price, String description) {
        if (itemsExist()) {
            itemToUpdate.setPrice(price);
            itemToUpdate.setDescription(description);
            // Write the updated menu items to the Excel file
            MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
            menuItemXlsxHelper.updateXlsx(itemToUpdate);
            System.out.println("Item successfully updated in menu!");
        }
    }

    // Remove menu item
    public void removeItem(MenuItem itemToRemove) {
        if (itemsExist()) {
            boolean removed = this.menuItems.removeIf(item -> item.getName().equals(itemToRemove.getName()));
            if (removed) {
                MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
                menuItemXlsxHelper.removeXlsx(itemToRemove.getId());
                System.out.println("Item successfully removed from menu!");
            } else {
                System.out.println("Menu item not found.");
            }
        }
    }
}