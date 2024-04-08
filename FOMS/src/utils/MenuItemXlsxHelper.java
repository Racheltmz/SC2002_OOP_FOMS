package utils;

import menu.MenuItem;
import exceptions.*;
import exceptions.IllegalArgumentException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MenuItemXlsxHelper extends BaseXlsxHelper  {

    /**
     * Path to Menu Items XLSX File in the data folder. Defaults to menu.xlsx.
     */
    private String menuItemXlsx = "menu_list.xlsx";

    /**
     * Singleton instance of this class.
     */
    private static MenuItemXlsxHelper mInstance;

    /**
     * Default Constructor to initialize this class with menu.xlsx as the XLSX file.
     */
    private MenuItemXlsxHelper() {
    }

    /**
     * Gets the singleton instance of MenuItemXlsxHelper that reads from menu.xlsx
     *
     * @return Instance of this class
     */
    public static MenuItemXlsxHelper getInstance() {
        if (mInstance == null) mInstance = new MenuItemXlsxHelper();
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
            workbook = WorkbookFactory.create(FileIOHelper.getFileInputStream(this.menuItemXlsx));
            Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet is where data is stored
            List<String[]> XlsxData = readAll(sheet, 1);
            ArrayList<MenuItem> items = new ArrayList<>();
            if (XlsxData.size() == 0) return items;
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
     * @param items ArrayList of Menu items to save.
     * @throws IOException Unable to write to file.
     */
    public void writeToXlsx(ArrayList<MenuItem> items) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Menu");
        String[] header = {"Name", "Price", "Branch", "Category"};
        writeHeader(header, sheet.createRow(0));
        for (int i = 0; i < items.size(); i++) {
            writeRow(items.get(i).toXlsx(), sheet.createRow(i + 1));
        }
        try (FileOutputStream fileOut = new FileOutputStream(this.menuItemXlsx)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    private void writeHeader(String[] header, Row row) {
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    private void writeRow(String[] data, Row row) {
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data[i]);
        }
    }
}



