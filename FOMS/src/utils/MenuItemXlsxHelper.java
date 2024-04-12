package utils;

import branch.Branch;
import branch.BranchDirectory;
import exceptions.DuplicateEntryException;
import menu.Menu;
import menu.MenuDirectory;
import menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MenuItemXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Menu Items XLSX File in the data folder. Defaults to menu_list.xlsx.
     */
    private final String menuItemXlsx;

    private final String[] header = {"id", "name", "price", "branch", "category", "description"};

    /**
     * Default Constructor to initialize this class with menu.xlsx as the XLSX file.
     */
    public MenuItemXlsxHelper() {
        this.menuItemXlsx = "menu_list.xlsx";
    }

    /**
     * Singleton instance of this class.
     */
    private static MenuItemXlsxHelper mInstance;

    /**
     * Gets the singleton instance of MenuItemXlsxHelper that reads from menu.xlsx
     *
     * @return Instance of this class
     */
    public static MenuItemXlsxHelper getInstance() {
        if (mInstance == null)
            mInstance = new MenuItemXlsxHelper();
        return mInstance;
    }

    /**
     * On initialisation, reads the XLSX file and parses it into an array list of menu item objects.
     *
     * @return ArrayList of Menu Item Objects.
     */
    public ArrayList<Menu> readFromXlsx() {
        // Initialise a list
        ArrayList<Menu> menuList = new ArrayList<>();
        BranchDirectory branchDirectory = BranchDirectory.getInstance();
        // Create a menu for each branch
        for (Branch branch : branchDirectory.getBranchDirectory())
            menuList.add(new Menu(branch));

        List<String[]> XlsxData = deserializeRecords(this.menuItemXlsx, this.header,6, 1);

        if (XlsxData.isEmpty()) {
            return menuList;
        }
        XlsxData.forEach((data) -> {
            UUID id = UUID.fromString(data[0]);
            String name = data[1];
            double price = Double.parseDouble(data[2]);
            String branch = data[3];
            String category = data[4];
            String description = data[5];

            // Add new item
            for (Menu menu : menuList) {
                if (Objects.equals(menu.getBranch().getBranchName(), branch)) {
                    menu.addItem(new MenuItem(id, name, price, branch, category, description), -1, true);
                }
            }
        });
        return menuList;
    }

    /**
     * Writes a menuItem record to the XLSX File.
     *
     * @param item MenuItem record to add
     * @param numExistingRecords Number of existing menuItem records
     */
    public void writeToXlsx(MenuItem item, int numExistingRecords) throws DuplicateEntryException {
        MenuDirectory menuDirectory = MenuDirectory.getInstance();
        boolean isExisting = menuDirectory
                .getMenu(item.getBranch())
                .itemExists(item.getName());
        if (!isExisting) {
            serializeRecord(this.menuItemXlsx, item.toXlsx(), numExistingRecords);
        } else {
            throw new DuplicateEntryException("Record not inserted: Duplicate item entered.");
        }
    }

    /**
     * Updates a menuItem record in the XLSX File.
     *
     * @param item MenuItem record to add
     */
    public void updateXlsx(MenuItem item) {
        serializeUpdate(this.menuItemXlsx, item.toXlsx(), item.getId());
    }

    /**
     * Deletes a menuItem record in the XLSX File.
     *
     * @param id ID of menuItem record to delete
     */
    public void removeXlsx(UUID id) {
        deleteRecord(this.menuItemXlsx, id);
    }
}
