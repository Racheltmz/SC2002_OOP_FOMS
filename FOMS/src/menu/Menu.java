package menu;

import branch.Branch;
import exceptions.DuplicateEntryException;
import utils.MenuItemXlsxHelper;

import java.util.ArrayList;

// TODO: CREATE VIEW for displayItems
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
        for (int i=0; i<this.menuItems.size(); i++) {
            MenuItem item = this.menuItems.get(i);
            System.out.printf("| %-3d | %-10s | %-20s | %-10s | %-50s |\n", i+1, item.getCategory(), item.getName(), item.getPrice(), item.getDescription());
        }
    }

    public boolean itemExists(String name) {
        for (MenuItem item: this.menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Add menu item
    public void addItem(MenuItem menuItem, int numExistingItems, boolean stored) {
        this.menuItems.add(menuItem);
        if (!stored) {
            try {
                MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
                menuItemXlsxHelper.writeToXlsx(menuItem, numExistingItems);
                System.out.println("Menu item added successfully.");
            } catch (DuplicateEntryException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Update menu item
    public void updateItem(MenuItem itemToUpdate, double price, String description) {
        itemToUpdate.setPrice(price);
        itemToUpdate.setDescription(description);
        // Write the updated menu items to the Excel file
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        menuItemXlsxHelper.updateXlsx(itemToUpdate);
        System.out.println("Menu item updated successfully.");
    }

    // Remove menu item
    public void removeItem(MenuItem itemToRemove) {
        boolean removed = this.menuItems.removeIf(item -> item.getName().equals(itemToRemove.getName()));
        if (removed) {
            MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
            menuItemXlsxHelper.removeXlsx(itemToRemove.getId());
            System.out.println("Menu item removed successfully.");
        } else {
            System.out.println("Menu item not found.");
        }
    }
}