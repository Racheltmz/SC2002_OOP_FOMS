package utils;

import menu.MenuItem;
import order.Order;

import java.util.ArrayList;
import java.util.List;

// TODO: CREATE ONE FOR ORDER AND HANDLE IF FILE DOESN'T EXIST, CREATE NEW
public class OrderXlsxHelper extends BaseXlsxHelper {
    /**
     * Path to Order XLSX File in the data folder. Defaults to order_list.xlsx.
     */
    private String orderXlsx;

    private final String[] header = {"id", "name", "price", "branch", "category", "description"};

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
        List<String[]> XlsxData = deserializeRecords(this.orderXlsx, this.header, 5, 1);

        if (XlsxData.isEmpty()) {
            serializeHeader(this.orderXlsx, this.header);
            return orders;
        }
        XlsxData.forEach((data) -> {
            String name = data[0];
            double price = Double.parseDouble(data[1]);
            String branch = data[2];
            String category = data[3];
            String description = data[4];
        });
        return orders;
    }

    /**
     * Writes to the XLSX File.
     *
     */
    public void writeToXlsx(MenuItem newItem, int numExistingRecords) {
        serializeRecord(this.orderXlsx, newItem.toXlsx(), numExistingRecords);
    }

}
