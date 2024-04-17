package menu;

import java.util.ArrayList;
import java.util.Objects;

import branch.BranchDirectory;
import exceptions.EmptyListException;
import exceptions.ExcelFileNotFound;
import exceptions.ItemNotFoundException;
import io.MenuItemXlsxHelper;

public class MenuDirectory {
    private final ArrayList<Menu> menuDirectory;
    private static MenuDirectory menuSingleton = null;

    private MenuDirectory() throws ExcelFileNotFound {
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        this.menuDirectory = menuItemXlsxHelper.readFromXlsx();
    }

    public static MenuDirectory getInstance() throws ExcelFileNotFound {
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

    public void displayMenuByBranch() throws ExcelFileNotFound {
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
}
