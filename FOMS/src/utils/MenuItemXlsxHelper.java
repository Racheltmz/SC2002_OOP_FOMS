package utils;

import branch.Branch;
import branch.BranchDirectory;
import menu.Menu;
import menu.MenuItem;
import exceptions.IllegalArgumentException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuItemXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Menu Items XLSX File in the data folder. Defaults to menu_list.xlsx.
     */
    private String menuItemXlsx;

    /**
     * Default Constructor to initialize this class with menu.xlsx as the XLSX file.
     */
    public MenuItemXlsxHelper() {
        this.menuItemXlsx = getDataDir() + "menu_list.xlsx";
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
     * @throws IOException Unable to read from file.
     */
    public ArrayList<MenuItem> readFromXlsx() throws IOException {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(FileIOHelper.getFileInputStream(menuItemXlsx));
            Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet is where data is stored
            List<String[]> XlsxData = readAll(sheet, 1);
            ArrayList<MenuItem> items = new ArrayList<>();
            if (XlsxData.isEmpty())
                return items;
            XlsxData.forEach((str) -> {
                try {
                    items.add(new MenuItem(str));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            return items;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    /**
     * Writes to the XLSX File.
     *
     * @throws IOException Unable to write to file.
     */
    public void writeToXlsx(int numExistingRecords, MenuItem newItem) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(this.menuItemXlsx));
        Sheet sheet = workbook.getSheetAt(0);
        writeRow(newItem.toXlsx(), sheet.createRow(numExistingRecords)); //.toXlsx()
        try (FileOutputStream fileOut = new FileOutputStream(this.menuItemXlsx)) {
            // Write to file...
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    // Initialise menu records based on the branch
    public ArrayList<Menu> initialiseRecords() {
        // Initialise a list
        ArrayList<Menu> menuList = new ArrayList<Menu>();
        BranchDirectory branchDirectory = BranchDirectory.getInstance();
        // Create a menu for each branch
        for (Branch branch : branchDirectory.getBranchDirectory())
            menuList.add(new Menu(branch));

        XSSFSheet menuSheet = getSheet(this.menuItemXlsx);
        // Iterate all rows
        for (int i = 1; i < menuSheet.getPhysicalNumberOfRows(); i++) {
            Row row = menuSheet.getRow(i);
            if (row != null && row.getLastCellNum() >= 4) { // Ensure at least 4 cells in a row
                // Get values from rows
                Cell nameCell = row.getCell(0);
                Cell priceCell = row.getCell(1);
                Cell branchCell = row.getCell(2);
                Cell categoryCell = row.getCell(3);

                // Ensure cells are not empty
                if (nameCell != null && priceCell != null && branchCell != null && categoryCell != null) {
                    // Convert to respective data type
                    String name = nameCell.toString().trim();
                    double price = priceCell.getNumericCellValue();
                    String branch = branchCell.toString().trim();
                    String category = categoryCell.toString().trim();

                    // Add new item
                    for (Menu menu : menuList) {
                        if (Objects.equals(menu.getBranch().getBranchName(), branch)) {
                            menu.addItem(new MenuItem(name, price, branch, category));
                        }
                    }
                }
            }
        }
        return menuList;
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
