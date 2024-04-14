package menu;

import static branch.BranchDirectory.*;
import static utils.Initialisation.*;

import java.util.ArrayList;
import java.util.Objects;

import branch.BranchDirectory;

public class MenuDirectory {
    private ArrayList<Menu> menuDirectory;
    private static MenuDirectory menuSingleton = null;

    private MenuDirectory() {
        this.menuDirectory = initialiseMenuRecords();
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
