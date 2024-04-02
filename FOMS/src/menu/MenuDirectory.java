package menu;

import management.Company;

import java.util.ArrayList;
import java.util.Objects;

import static branch.BranchDirectory.getBranchByUserInput;

// TODO: create view for displaymenubybranch
// TODO: CHECK IF NEED queryBranch interface for displayitemsby branch
// Records of menu
public class MenuDirectory {
    // Attributes
    private ArrayList<Menu> menuDirectory;

    // Constructor
    public MenuDirectory(ArrayList<Menu> menuDirectory) {
        this.menuDirectory = menuDirectory;
    }

    // Functionalities
    // Get all menus
    public ArrayList<Menu> getMenuDirectory() {
        return menuDirectory;
    }

    // Get menu by branch name
    public Menu getMenu(String branchName) {
        // Return staff object if it can be found
        for (Menu menu : this.menuDirectory) {
            if (Objects.equals(menu.getBranch().getBranchName(), branchName))
                return menu;
        }
        // Return null if menu cannot be found
        return null;
    }

    // Display menu based on branch selected by customer
    public static void displayMenuByBranch(Company company) {
        // Get menu by user's selection of branch
        String branch = getBranchByUserInput(company.getBranchDirectory());
        Menu menu = company.getMenuDirectory().getMenu(branch);
        if (menu == null) {
            System.out.println("No items found for the specified branch.");
            return;
        }
        System.out.println("Menu items in branch " + branch + ":");
        menu.displayItems();
    }

    // Get number of menu items
    public int getNumMenuItems(String branch) {
        return getMenu(branch).getMenuItems().size();
    }
}
