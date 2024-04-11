package utils;

import branch.Branch;
import branch.BranchDirectory;
import menu.Menu;
import menu.MenuItem;
import order.Order;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static utils.FileIOHelper.getSheet;

// TODO: CREATE ONE FOR ORDER AND HANDLE IF FILE DOESN'T EXIST, CREATE NEW
public class OrderXlsxHelper extends BaseXlsxHelper {
    /**
     * Path to Order XLSX File in the data folder. Defaults to order_list.xlsx.
     */
    private String orderXlsx;

    /**
     * Default Constructor to initialize this class with menu.xlsx as the XLSX file.
     */
    public OrderXlsxHelper() {
        this.orderXlsx = "order_lists.xlsx";
    }

    /**
     * Singleton instance of this class.
     */
    private static OrderXlsxHelper orderInstance;

    /**
     * Gets the singleton instance of MenuItemXlsxHelper that reads from menu.xlsx
     *
     * @return Instance of this class
     * @throws IOException
     */
    public static OrderXlsxHelper getInstance() {
        if (orderInstance == null)
            orderInstance = new OrderXlsxHelper();
        return orderInstance;
    }

    /**
     * Reads the XLSX file and parses it into an array list of menu item objects.
     *
     * @return ArrayList of Menu Item Objects.
     */
    public ArrayList<Order> readFromXlsx() {
        // Initialise a list
        ArrayList<Order> orders = new ArrayList<Order>();
        XSSFSheet sheet = getSheet(this.orderXlsx);
        List<String[]> XlsxData = deserializeRecords(sheet, 1);

        if (XlsxData.isEmpty())
            return orders;
        XlsxData.forEach((data) -> {
            if (data.length == 5) {
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                String branch = data[2];
                String category = data[3];
                String description = data[4];
            }
        });
        return orders;
    }

    /**
     * Writes to the XLSX File.
     *
     * @throws IOException Unable to write to file.
     */
    public void writeToXlsx(MenuItem newItem, int numExistingRecords) throws IOException {
        String[] header = {"id", "name", "price", "branch", "category", "description"};
        serializeRecord(this.orderXlsx, newItem.toXlsx(), header, numExistingRecords);
    }

}
