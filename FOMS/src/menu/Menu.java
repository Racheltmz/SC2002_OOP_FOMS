package menu;

import branch.Branch;
import exceptions.DuplicateEntryException;
import exceptions.EmptyListException;
import io.MenuItemXlsxHelper;

import java.util.ArrayList;

/**
 * Menu class stores the available MenuItems in the Branch.
 */
public class Menu {
    // Attributes
    private Branch branch;
    private ArrayList<MenuItem> menuItems;

    /**
     * Constructs a Menu.
     *
     * @param branch Branch in which the Menu will be initialised.
     */
    public Menu(Branch branch) {
        this.branch = branch;
        this.menuItems = new ArrayList<>();
    }

    /**
     * Gets the Branch of this Menu.
     *
     * @return The Branch of this menu.
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * Gets the MenuItems in this Menu.
     *
     * @return An ArrayList of the MenuItems in this Menu.
     */
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Prints the existing MenuItems in this Menu in a tabled manner.
     */
    public void displayItems() {
        System.out.printf("| No. | %-10s | %-20s | %-10s | %-50s |\n", "Category", "Name", "Price ($)", "Description");
        System.out.println("=".repeat(110));
        for (int i = 0; i < this.menuItems.size(); i++) {
            MenuItem item = this.menuItems.get(i);
            System.out.printf("| %-3d | %-10s | %-20s | %-10s | %-50s |\n", i + 1, item.getCategory(), item.getName(),
                    item.getPrice(), item.getDescription());
        }
    }

    /**
     * Verifies if MenuItems exist in this Menu.
     * If not, prints an error message for the customer to view.
     *
     * @return true if items exist, false if not.
     */
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

    /**
     * Verifies if the MenuItem exists in this Menu.
     *
     * @param name The name of the MenuItem to check for.
     * @return true if it exists, false if not.
     */
    public boolean itemExistsByName(String name) {
        for (MenuItem item : this.menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new MenuItem to this Menu, then updates the Excel sheet after verifying it is not a duplicate item.
     *
     * @param menuItem The MenuItem to be added into this Menu.
     * @param stored The boolean value to check if the MenuItem is a duplicate item. True if it is a duplicate, false if not.
     * @param numExistingItems The number of existing items to add the MenuItem in the Excel sheet at the index after.
     */
    public void addItem(MenuItem menuItem, int numExistingItems, boolean stored) {
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

    /**
     * Verifies if a MenuItem exists, then updates its details in the Excel sheet.
     *
     * @param itemToUpdate The MenuItem to be updated.
     */
    public void updateItem(MenuItem itemToUpdate) {
        if (itemsExist()) {
            // Write the updated menu items to the Excel file
            MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
            menuItemXlsxHelper.updateXlsx(itemToUpdate);
            System.out.println("Item successfully updated in menu!");
        }
    }

    /**
     * Verifies if a MenuItem exists, then removes it from the Excel sheet.
     *
     * @param itemToRemove The MenuItem to be removed.
     */
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