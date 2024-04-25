package menu;

import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import io.MenuItemXlsxHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static utils.ValidateHelper.validateIntRange;

/**
 * Handles operations on Menu records and stores existing Menu records.
 */
public class MenuDirectory {
    private final ArrayList<Menu> menuDirectory;
    private static MenuDirectory menuSingleton = null;

    private static final ArrayList<String> CATEGORIES = new ArrayList<>(Arrays.asList("Side", "Burger", "Set meal", "Drink"));

    /**
     * Singleton constructor that reads all Menu records from the storage file on initialisation.
     */
    private MenuDirectory() {
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        this.menuDirectory = menuItemXlsxHelper.readFromXlsx();
    }

    /**
     * Gets the instance for the Menu directory records.
     *
     * @return MenuDirectory instance.
     */
    public static MenuDirectory getInstance() {
        if (menuSingleton == null) {
            menuSingleton = new MenuDirectory();
        }
        return menuSingleton;
    }

    /**
     * Check if the directory has any menus
     *
     * @return boolean
     */
    public boolean menusExist() {
        try {
            if (this.menuDirectory.isEmpty())
                throw new EmptyListException("There are no menus. Please approach the staff for assistance.");
            else
                return true;
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Get the Menu of a specific Branch.
     *
     * @param branchName The Branch to retrieve the Menu from.
     * @return the Menu of the specific Branch.
     */
    public Menu getMenu(String branchName) {
        if (menusExist()) {
            try {
                for (Menu menu : this.menuDirectory) {
                    if (Objects.equals(menu.getBranch().getName(), branchName)) {
                        return menu;
                    }
                }
                throw new ItemNotFoundException("There is no menu for this branch. Please check the branch name.");
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Get the price of a MenuItem given its name and Branch.
     *
     * @param branch the Branch of the MenuItem.
     * @param name the name of the MenuItem.
     * @return the price of the MenuItem if found, else returns 0.
     */
    public double getPriceByNameAndBranch(String name, String branch) {
        if (menusExist()) {
            for (Menu menu : this.menuDirectory) {
                for (MenuItem menuitem : menu.getMenuItems()) {
                    if (Objects.equals(menuitem.getName(), name) && Objects.equals(menuitem.getBranch(), branch)) {
                        return menuitem.getPrice();
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Get the category of a MenuItem given its name and Branch.
     *
     * @param branch the Branch of the MenuItem.
     * @param name the name of the MenuItem.
     * @return the category of the MenuItem if found, else returns null.
     */
    public String getCategoryByNameAndBranch(String name, String branch) {
        if (menusExist()) {
            for (Menu menu : this.menuDirectory) {
                for (MenuItem menuitem : menu.getMenuItems()) {
                    if (Objects.equals(menuitem.getName(), name) && Objects.equals(menuitem.getBranch(), branch)) {
                        return menuitem.getCategory();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get the description of a MenuItem given its name and Branch.
     *
     * @param branch the Branch of the MenuItem.
     * @param name the name of the MenuItem.
     * @return the description of the MenuItem if found, else returns null.
     */
    public String getDescriptionByNameAndBranch(String name, String branch) {
        if (menusExist()) {
            for (Menu menu : this.menuDirectory) {
                for (MenuItem menuitem : menu.getMenuItems()) {
                    if (Objects.equals(menuitem.getName(), name) && Objects.equals(menuitem.getBranch(), branch)) {
                        return menuitem.getDescription();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Displays the Menu of a certain Branch.
     *
     * @param branch the Branch of the Menu.
     */
    public void displayMenuByBranch(String branch) {
        Menu menu = getMenu(branch);
        System.out.println("Menu items in branch " + branch + ":");
        menu.displayItems();
    }

    /**
     * Get the total number of MenuItems in this MenuDirectory.
     *
     * @return The number of MenuItems.
     */
    public int getNumAllMenuItems() {
        int count = 0;
        for (Menu menu: this.menuDirectory) {
            count += menu.getMenuItems().size();
        }
        return count;
    }

    /**
     * Get the existing categories.
     *
     * @return An ArrayList of the existing categories.
     */
    public static ArrayList<String> getCategories(){
        return CATEGORIES;
    }

    /**
     * Displays all the existing categories in an indexed manner.
     */
    public static void displayCategories(){
        ArrayList<String> categories = getCategories();

        // display categories
        System.out.println("Categories:");
        for (int i = 0; i < categories.size(); i++){
            System.out.printf("%d. %s\n", i + 1, categories.get(i));
        }

    }

    /**
     * Get a category by displaying existing categories and prompting user to select one.
     *
     * @return The selected category.
     */
    public static String getCategoryByUserInput(){
        ArrayList<String> categories = getCategories();
        int size = categories.size();

        displayCategories();

        // Get user's selection
        int categoryIndex = validateIntRange("Select Category: ", 1, size);
        return categories.get(categoryIndex - 1);
    }

    /**
     * Get a MenuItem by displaying available MenuItems and prompting user to select one.
     *
     * @return The selected MenuItem.
     * @param branchName The Branch to select the MenuItem from.
     */
    public MenuItem selectItem(String branchName) {
        // Get branches
        ArrayList<MenuItem> items = this.getMenu(branchName).getMenuItems();
        int size = items.size();
        MenuItem selection = null;
        // Get user's selection
        int menuItemIndex = validateIntRange("Select item (" + (size+1) + " to quit): ", 1, size+1);
        if (menuItemIndex != size+1) {
            selection = items.get(menuItemIndex - 1);
        }
        return selection;
    }

    /**
     * Removes existing MenuItems in a specific Branch after verifying it exists in that Branch.
     *
     * @param branchName The name of the Branch to retrieve the Menu from.
     * @param menuItems The ArrayList of MenuItems to refer to, and remove MenuItems one by one.
     */
    public void rmvMenuByBranch(ArrayList<MenuItem> menuItems, String branchName) {
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        for (MenuItem menuItemToRmv: menuItems) {
            this.menuDirectory.removeIf(menuItem -> menuItem.getBranch().equals(branchName));
            menuItemXlsxHelper.removeXlsx(menuItemToRmv.getId());
        }
    }
}
