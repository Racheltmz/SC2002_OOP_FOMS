package menu;

import branch.BranchDirectory;
import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import io.MenuItemXlsxHelper;

import java.util.ArrayList;
import java.util.Objects;

public class MenuDirectory {
    private final ArrayList<Menu> menuDirectory;
    private static MenuDirectory menuSingleton = null;

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

    public void displayMenuByBranch() {
        BranchDirectory branchDirectory = BranchDirectory.getInstance();
        String branch = branchDirectory.getBranchByUserInput().getName();
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
    public ArrayList<String> getCategories(){
        ArrayList<String> categories = new ArrayList<>();
        for (Menu menu: menuDirectory){
            for (MenuItem item : menu.getMenuItems()){
                if (!categories.contains(item.getCategory())){
                    categories.add(item.getCategory());
                }
            }
        }
        return categories;
    }

    public void displayCategories(){
        ArrayList<String> categories = getCategories();

        if (categories.isEmpty()){
            System.out.println("No categories available.");
            return;
        }

        // display categories
        System.out.println("Categories:");
        for (int i = 0; i < categories.size(); i++){
            System.out.printf("%d. %s\n", i + 1, categories.get(i));
        }

    }

    public String getCategoryByUserInput(){
        ArrayList<String> categories = getCategories();
        if(categories.isEmpty()){
            System.out.println("No categories available to select.");
            return null;
        }
        
        displayCategories();

        String selectedCategory = null;
        boolean success = false;

        do {
            try {
                // Get user's selection
                int categoryIndex = validateInt("Select Category: ");
                selectedCategory = categories.get(categoryIndex - 1);
                success = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid value, please enter again.");
            }
        } while (!success);
        return selectedCategory;
    } 
}
