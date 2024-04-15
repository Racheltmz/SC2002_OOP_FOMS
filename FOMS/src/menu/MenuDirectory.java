package menu;

import static branch.BranchDirectory.*;
import static utils.Initialisation.*;

import java.util.ArrayList;
import java.util.Objects;

import branch.BranchDirectory;
import exceptions.MenuItemNotFoundException;

public class MenuDirectory {
    private ArrayList<Menu> menuDirectory;
    private static MenuDirectory menuSingleton = null;

    private MenuDirectory() {
        try {
            this.menuDirectory = initialiseMenuRecords();
        } catch (Exception e) {
            System.out.println("Error while initializing menu records: " + e.getMessage());
            this.menuDirectory = new ArrayList<>();
        }
    }

    public static MenuDirectory getInstance() {
        if (menuSingleton == null) {
            menuSingleton = new MenuDirectory();
        }
        return menuSingleton;
    }

    public Menu getMenu(String branchName) {
        try {
            for (Menu menu : this.menuDirectory) {
                if (Objects.equals(menu.getBranch().getBranchName(), branchName)) {
                    return menu;
                }
            }
            throw new MenuItemNotFoundException("Menu item not found in the branch: " + branchName);
        } catch (MenuItemNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void displayMenuByBranch() {
        try {
            BranchDirectory branchDirectory = BranchDirectory.getInstance();
            String branch = getBranchByUserInput(branchDirectory);
            Menu menu = getMenu(branch);
            if (menu != null) {
                System.out.println("Menu items in branch " + branch + ":");
                menu.displayItems();
            } else {
                System.out.println("No menu found for branch " + branch);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumAllMenuItems() {
        int count = 0;
        try {
            for (Menu menu : this.menuDirectory) {
                count += menu.getMenuItems().size();
            }
        } catch (NullPointerException e) {
            System.out.println("Error occurred while calculating the number of all menu items: " + e.getMessage());
        }
        return count;
    }

    public int getNumMenuItems(String branch) {
        try {
            Menu menu = getMenu(branch);
            if (menu != null) {
                return menu.getMenuItems().size();
            } else {
                throw new MenuItemNotFoundException("Menu is empty for branch " + branch);
            }
        } catch (MenuItemNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
