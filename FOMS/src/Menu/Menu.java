package Menu;

import Management.Branch;
import Management.Company;

import java.util.ArrayList;

import static Validation.ValidateDataType.validateInt;

public class Menu {
    // Attributes
    private String branch;
    private ArrayList<Item> menuItems;

    // Constructor
    public Menu(String branch) {
        this.branch = branch;
        this.menuItems = new ArrayList<>();
    }

    // Getters
    public String getBranch() {
        return branch;
    }

    public ArrayList<Item> getMenuItems() {
        return menuItems;
    }

    public void displayMenuItems() {
        System.out.printf("| No. | %-10s | %-20s | %-10s |\n", "Category", "Name", "Price ($)");
        System.out.println("-".repeat(55));
        for (int i=0; i<this.menuItems.size(); i++) {
            Item item = this.menuItems.get(i);
            System.out.printf("| %-3d | %-10s | %-20s | %-10s |\n", i+1, item.getCategory(), item.getName(), item.getPrice());
        }
    }

    public static void displayItemsByBranch(Company company) {
        // Display branches
        company.displayBranches();
        // Get branches
        ArrayList<Branch> branches = company.getBranches();
        try {
            // Get user's selection
            int branchIndex = validateInt("Select Branch: ");
            // Get branch name
            String branch = branches.get(branchIndex-1).getBranchName();
            Menu menu = company.getMenu(branch);
            if (menu == null) {
                System.out.println("No items found for the specified branch.");
                return;
            }
            System.out.println("Menu items in branch " + branch + ":");
            menu.displayMenuItems();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid value, please enter again.");
        }
    }

    // Add menu item
    public void addItem(Item menuItem) {
        menuItems.add(menuItem);
    }

    // Update menu item
    public void updateItem(Item itemToUpdate, double price, String description) {
        itemToUpdate.setPrice(price);
    }

    // Remove menu item
    public void removeItem(Item itemToRemove) {
        menuItems.removeIf(item -> item.getName().equals(itemToRemove.getName()));
    }
//
//    public ArrayList<Item> getItemsByCategory(String category) {
//        ArrayList<Item> itemsByCategory = new ArrayList<>();
//        for (Item item : menuItems) {
//            if (item.getCategory().equals(category)) {
//                itemsByCategory.add(item);
//            }
//        }
//        return itemsByCategory;
//    }
//
//    public ArrayList<Item> getItemsByBranchAndCategory(String branch, String category) {
//        ArrayList<Item> itemsByBranchAndCategory = new ArrayList<>();
//        for (Item item : menuItems) {
//            if (item.getBranch().equals(branch) && item.getCategory().equals(category)) {
//                itemsByBranchAndCategory.add(item);
//            }
//        }
//        return itemsByBranchAndCategory;
//    }
}