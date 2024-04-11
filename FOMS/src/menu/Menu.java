package menu;

import branch.Branch;
import utils.MenuItemXlsxHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

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

    // Add menu item
    public void addItem(MenuItem menuItem, int numExistingItems, boolean stored) throws IOException {
        menuItems.add(menuItem);
        if (!stored) {
            MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
            menuItemXlsxHelper.writeToXlsx(menuItem, numExistingItems);
            System.out.println("Menu item added successfully.");
        }
    }

    // Update menu item
    public void updateItem(MenuItem itemToUpdate, double price, String description) throws IOException {
        itemToUpdate.setPrice(price);
        itemToUpdate.setDescription(description);
        // Write the updated menu items to the Excel file
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        menuItemXlsxHelper.updateXlsx(itemToUpdate);
        System.out.println("Menu item updated successfully.");
    }

    // Remove menu item
    public void removeItem(MenuItem itemToRemove) {
        boolean removed = menuItems.removeIf(item -> item.getName().equals(itemToRemove.getName()));
        if (removed)
            System.out.println("Menu item removed successfully.");
        else
            System.out.println("Menu item not found.");
    }
}