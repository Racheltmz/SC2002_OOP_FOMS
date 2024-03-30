import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FOMSApp {
    public static void main(String[] args) {
        try {
            List<Item> itemList = loadItemsFromExcel("./data/menu_list.xlsx");
         //Item.printItemsGroupedByCategory(itemList);
            Item.printItemsGroupedByBranch(itemList);
            //Item.printItemsGroupedByName(itemList);
        } catch (IOException e) {
            System.out.println("Error reading the Excel file: " + e.getMessage());
        }
    }

    public static List<Item> loadItemsFromExcel(String filePath) throws IOException {
        List<Item> itemList = new ArrayList<>();
        FileInputStream menuFile = new FileInputStream(new File(filePath));
        XSSFWorkbook menuWorkbook = new XSSFWorkbook(menuFile);
        XSSFSheet menuSheet = menuWorkbook.getSheetAt(0);

        for (int i = 1; i < menuSheet.getPhysicalNumberOfRows(); i++) {
            Row row = menuSheet.getRow(i);
            if (row != null && row.getLastCellNum() >= 4) { // Ensure at least 4 cells in a row
                Cell nameCell = row.getCell(0);
                Cell priceCell = row.getCell(1);
                Cell branchCell = row.getCell(2);
                Cell categoryCell = row.getCell(3);

                if (nameCell != null && priceCell != null && branchCell != null && categoryCell != null) {
                    String name = nameCell.toString().trim();
                    double price = priceCell.getNumericCellValue();
                    String branch = branchCell.toString().trim();
                    String category = categoryCell.toString().trim();
                    itemList.add(new Item(name, price, branch, category));
                }
            }
        }

        menuWorkbook.close();
        menuFile.close();
        return itemList;
    }

}
