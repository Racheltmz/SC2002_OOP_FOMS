package menu;

import management.Company;
import branch.BranchDirectory;
import exceptions.ItemNotFoundException;
import exceptions.MenuItemNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class MenuDirectory {
    private ArrayList<Menu> menuDirectory;

    public MenuDirectory(ArrayList<Menu> menuDirectory) {
        this.menuDirectory = menuDirectory;
    }

    public ArrayList<Menu> getMenuDirectory() {
        return menuDirectory;
    }

    public Menu getMenu(String branchName) throws MenuItemNotFoundException, ItemNotFoundException {
        for (Menu menu : this.menuDirectory) {
            if (Objects.equals(menu.getBranch().getBranchName(), branchName)) {
                return menu;
            }
        }
        throw new MenuItemNotFoundException("Menu item not found in the branch: " + branchName);
    }

    public static void displayMenuByBranch(Company company) {
        String branch = BranchDirectory.getBranchByUserInput(company.getBranchDirectory());
        Menu menu = null;
        try {
            menu = company.getMenuDirectory().getMenu(branch);
            if (menu == null) {
                throw new ItemNotFoundException("Menu is empty for branch " + branch);
            }
            System.out.println("Menu items in branch " + branch + ":");
            menu.displayItems();
        } catch (MenuItemNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumMenuItems(String branch) {
        try {
            Menu menu = getMenu(branch);
            if (menu == null) {
                throw new ItemNotFoundException("Menu is empty for branch " + branch);
            }
            return menu.getMenuItems().size();
        } catch (MenuItemNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
