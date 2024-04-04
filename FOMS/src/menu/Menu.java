package menu;

import branch.Branch;

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
        System.out.printf("| No. | %-10s | %-20s | %-10s |\n", "Category", "Name", "Price ($)");
        System.out.println("-".repeat(55));
        for (int i=0; i<this.menuItems.size(); i++) {
            MenuItem item = this.menuItems.get(i);
            System.out.printf("| %-3d | %-10s | %-20s | %-10s |\n", i+1, item.getCategory(), item.getName(), item.getPrice());
        }
    }

    // Add menu item
    public void addItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    // Update menu item
    // TODO: CHECK DESCRIPTION
    public void updateItem(MenuItem itemToUpdate, double price, String description) {
        itemToUpdate.setPrice(price);
       // itemToUpdate.setDescription(description);
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