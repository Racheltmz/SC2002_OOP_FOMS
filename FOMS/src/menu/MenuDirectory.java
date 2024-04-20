package menu;

import branch.BranchDirectory;
import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import io.MenuItemXlsxHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static utils.ValidateHelper.validateIntRange;

public class MenuDirectory {
    private final ArrayList<Menu> menuDirectory;
    private static MenuDirectory menuSingleton = null;

    private static final ArrayList<String> categories = new ArrayList<>(Arrays.asList("Side", "Burger", "Set meal", "Drink"));

    private MenuDirectory() {
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        this.menuDirectory = menuItemXlsxHelper.readFromXlsx();
    }

    public static MenuDirectory getInstance() {
        if (menuSingleton == null) {
            menuSingleton = new MenuDirectory();
        }
        return menuSingleton;
    }

    public ArrayList<Menu> getMenuDirectory() {
        return this.menuDirectory;
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

    public void displayMenuByBranch(String branch) {
        Menu menu = getMenu(branch);
        System.out.println("Menu items in branch " + branch + ":");
        menu.displayItems();
    }

    public int getNumAllMenuItems() {
        int count = 0;
        for (Menu menu: this.menuDirectory) {
            count += menu.getMenuItems().size();
        }
        return count;
    }

    public int getNumMenuItems(String branch) {
        Menu menu = getMenu(branch);
        return menu.getMenuItems().size();
    }

    // get categories from menu items
    // TODO: fixed list
    public static ArrayList<String> getCategories(){
        return categories;
    }

    public static void displayCategories(){
        ArrayList<String> categories = getCategories();

        // display categories
        System.out.println("Categories:");
        for (int i = 0; i < categories.size(); i++){
            System.out.printf("%d. %s\n", i + 1, categories.get(i));
        }

    }

    public static String getCategoryByUserInput(){
        ArrayList<String> categories = getCategories();
        int size = categories.size();

        displayCategories();

        // Get user's selection
        int categoryIndex = validateIntRange("Select Category: ", 1, size);
        String selectedCategory = categories.get(categoryIndex - 1);
        return selectedCategory;
    }

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

    public void rmvMenuByBranch(ArrayList<MenuItem> menuItems, String branchName) {
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        for (MenuItem menuItemToRmv: menuItems) {
            this.menuDirectory.removeIf(menuItem -> menuItem.getBranch().equals(branchName));
            menuItemXlsxHelper.removeXlsx(menuItemToRmv.getId());
        }
    }
}
