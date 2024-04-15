package menu;

import java.util.ArrayList;

import branch.Branch;

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
        try{
        System.out.printf("| No. | %-10s | %-20s | %-10s |\n", "Category", "Name", "Price ($)");
        System.out.println("-".repeat(55));
        for (int i=0; i<this.menuItems.size(); i++) {
            MenuItem item = this.menuItems.get(i);
            System.out.printf("| %-3d | %-10s | %-20s | %-10s |\n", i+1, item.getCategory(), item.getName(), item.getPrice());
        }
    } catch (Exception e) {
        System.out.println("Unable to display menu items:" + e.getMessage());
    }
    }

    // Add menu item
    public void addItem(MenuItem menuItem) {
        try{
        menuItems.add(menuItem);
        }catch (Exception e) {
            System.out.println("Unable to add a menu item: " + e.getMessage());
        }
    }

    // Update menu item
    // TODO: CHECK DESCRIPTION
    public void updateItem(MenuItem itemToUpdate, double price, String description) {
        try{
        itemToUpdate.setPrice(price);
       // itemToUpdate.setDescription(description);
        System.out.println("Menu item updated successfully.");
        }catch (Exception e) {
            System.out.println("An error occured while updating a menu item: " + e.getMessage());

        }
    }

    // Remove menu item
    public void removeItem(MenuItem itemToRemove) {
        try {
            boolean removed = menuItems.removeIf(item -> item.getName().equals(itemToRemove.getName()));
            if (removed)
                System.out.println("Menu item removed successfully.");
            else
                System.out.println("Menu item not found.");
        } catch (Exception e) {
            System.out.println("An error occurred while removing a menu item: " + e.getMessage());
        }
    }
}