package utils;

import branch.Branch;
import branch.BranchDirectory;
import menu.Menu;
import menu.MenuItem;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static utils.FileIOHelper.getSheet;

public class MenuItemXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Menu Items XLSX File in the data folder. Defaults to menu_list.xlsx.
     */
    private String menuItemXlsx;

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
     * @throws IOException
     */
    public static MenuItemXlsxHelper getInstance() {
        if (mInstance == null)
            mInstance = new MenuItemXlsxHelper();
        return mInstance;
    }

    /**
     * Reads the XLSX file and parses it into an array list of menu item objects.
     *
     * @return ArrayList of Menu Item Objects.
     */
    public ArrayList<Menu> readFromXlsx() {
        // Initialise a list
        ArrayList<Menu> menuList = new ArrayList<Menu>();
        BranchDirectory branchDirectory = BranchDirectory.getInstance();
        // Create a menu for each branch
        for (Branch branch : branchDirectory.getBranchDirectory())
            menuList.add(new Menu(branch));

        XSSFSheet sheet = getSheet(this.menuItemXlsx);
        List<String[]> XlsxData = deserializeRecords(sheet, 1);

        if (XlsxData.isEmpty())
            return menuList;
        XlsxData.forEach((data) -> {
            if (data.length == 6) {
                UUID id = UUID.fromString(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                String branch = data[3];
                String category = data[4];
                String description = data[5];

                // TODO: INEFFICIENT IF HV TIME HANDLE
                // Add new item
                for (Menu menu : menuList) {
                    if (Objects.equals(menu.getBranch().getBranchName(), branch)) {
                        try {
                            menu.addItem(new MenuItem(id, name, price, branch, category, description), -1, true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        return menuList;
    }

    /**
     * Writes to the XLSX File.
     *
     * @throws IOException Unable to write to file.
     */
    public void writeToXlsx(MenuItem item, int numExistingRecords) throws IOException {
        String[] header = {"id", "name", "price", "branch", "category", "description"};
        serializeRecord(this.menuItemXlsx, item.toXlsx(), header, numExistingRecords);
    }

    public void updateXlsx(MenuItem item) throws IOException {
        serializeUpdate(this.menuItemXlsx, item.toXlsx(), item.getId());
    }
    //    public void saveAll(ArrayList<MenuItem> menuItems) {
//        try {
//            writeToXlsx(menuItems);
//            System.out.println("Menu items saved to menu_list.xlsx.");
//        } catch (IOException e) {
//            System.out.println("Failed to save menu items to menu_list.xlsx: " + e.getMessage());
//        }
//    }
}
