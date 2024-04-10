package utils;

import menu.MenuItem;
import exceptions.IllegalArgumentException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    // Constructor with custom file path
    public MenuItemXlsxHelper(String filePath) {
        this.menuItemXlsx = filePath;
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
            List<String[]> xlsxData = readAll(sheet, 1);
            ArrayList<MenuItem> items = new ArrayList<>();
            if (xlsxData.isEmpty())
                return items;
            xlsxData.forEach((str) -> {
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
        System.out.println("hello " + numExistingRecords + " " + newItem.getName());
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(this.menuItemXlsx));
        Sheet sheet = workbook.getSheetAt(0);
        writeRow(newItem.toXlsx(), sheet.createRow(numExistingRecords));
        try (FileOutputStream fileOut = new FileOutputStream(this.menuItemXlsx)) {
            // Write to file...
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
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
