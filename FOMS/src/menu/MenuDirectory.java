package menu;

import branch.BranchDirectory;
import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;
import utils.MenuItemXlsxHelper;

import java.util.ArrayList;
import java.util.Objects;

import static branch.BranchDirectory.getBranchByUserInput;

public class MenuDirectory {
    private ArrayList<Menu> menuDirectory;
    private static MenuDirectory menuSingleton = null;

    private MenuDirectory() {
        MenuItemXlsxHelper menuItemXlsxHelper = MenuItemXlsxHelper.getInstance();
        this.menuDirectory = menuItemXlsxHelper.initialiseRecords();
    }

    public static MenuDirectory getInstance() {
        if (menuSingleton == null) {
            menuSingleton = new MenuDirectory();
        }
        return menuSingleton;
    }

    public Menu getMenu(String branchName) {
        for (Menu menu : this.menuDirectory) {
            if (Objects.equals(menu.getBranch().getBranchName(), branchName)) {
                return menu;
            }
        }
        return null; // TODO: FIX THE EXCEPTIONS HERE
//        throw new MenuItemNotFoundException("Menu item not found in the branch: " + branchName);
    }

    public void displayMenuByBranch() {
        BranchDirectory branchDirectory = BranchDirectory.getInstance();
        String branch = getBranchByUserInput(branchDirectory);
        Menu menu;
//        try {
        menu = getMenu(branch);
//            if (menu == null) {
//                throw new ItemNotFoundException("Menu is empty for branch " + branch);
//            }
        System.out.println("Menu items in branch " + branch + ":");
        menu.displayItems();
//        } catch (MenuItemNotFoundException e) {
//            System.out.println(e.getMessage());
//        } catch (ItemNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public int getNumAllMenuItems() {
        int count = 0;
        for (Menu menu: this.menuDirectory) {
            count += menu.getMenuItems().size();
        }
        return count;
    }
    public int getNumMenuItems(String branch) {
//        try {
            Menu menu = getMenu(branch);
//            if (menu == null) {
//                throw new ItemNotFoundException("Menu is empty for branch " + branch);
//            }
            return menu.getMenuItems().size();
//        }
//        catch (MenuItemNotFoundException e) {
//            System.out.println(e.getMessage());
//            return 0;
//        } catch (ItemNotFoundException e) {
//            System.out.println(e.getMessage());
//            return 0;
//        }
    }
}
