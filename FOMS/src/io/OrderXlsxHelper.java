package io;

import menu.MenuDirectory;
import menu.MenuItem;
import order.Order;
import order.OrderStatus;
import payment.Payment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderXlsxHelper extends BaseXlsxHelper {
    /**
     * Path to Order XLSX File in the data folder. Defaults to order_list.xlsx.
     */
    private final String orderXlsx = "order_list.xlsx";

    /**
     * Headers for order.
     */
    private final String[] header = {"id", "branch", "items", "customisations", "takeaway", "status", "payment"};

    /**
     * Default Constructor.
     */
    public OrderXlsxHelper() {}

    /**
     * Singleton instance of order xlsx helper.
     */
    private static OrderXlsxHelper orderInstance;

    /**
     * Gets the singleton instance of OrderXlsxHelper that reads from menu_list.xlsx
     *
     * @return Instance of this class
     */
    public static OrderXlsxHelper getInstance() {
        if (orderInstance == null)
            orderInstance = new OrderXlsxHelper();
        return orderInstance;
    }

    /**
     * On initialisation, reads the XLSX file and parses it into an ArrayList of Order objects.
     *
     * @return ArrayList of Order Objects.
     */
    public ArrayList<Order> readFromXlsx() {
        // Initialise a list
        ArrayList<Order> orders = new ArrayList<>();
        MenuDirectory menuDirectory = MenuDirectory.getInstance();

        // Deserialize records
        List<String[]> XlsxData = deserializeRecords(this.orderXlsx, 8, 1);
        if (XlsxData.isEmpty()) {
            serializeHeader(this.orderXlsx, this.header);
            return orders;
        }

        // Add order
        XlsxData.forEach((data) -> {
            String orderID = data[0];
            String branch = data[1];
            String[] itemStrings = data[2].split(", ");
            String[] customisations = data[3].split(", ");
            boolean takeaway = Boolean.parseBoolean(data[4]);
            double totalAmount = Double.parseDouble(data[5]);
            String paymentMtd = data[6];
            OrderStatus status = OrderStatus.valueOf(data[7]);

            // Construct MenuItem objects from itemStrings
            ArrayList<MenuItem> items = new ArrayList<>();
            for (String itemString : itemStrings) {
                // Construct MenuItem object using itemString (you need to implement this logic)
                MenuItem item = new MenuItem(itemString, menuDirectory.getPriceByNameAndBranch(itemString, branch), branch, menuDirectory.getCategoryByNameAndBranch(itemString, branch), menuDirectory.getDescriptionByNameAndBranch(itemString, branch));
                items.add(item);
            }

            // Convert customisations array to ArrayList<String>
            ArrayList<String> customisationsList = new ArrayList<>(Arrays.asList(customisations));

            // Construct Order object using retrieved data
            Order order = new Order(orderID, branch, items, customisationsList, takeaway, totalAmount, paymentMtd);
            order.setStatus(status);

            orders.add(order);
        });
        return orders;
    }

    /**
     * Writes an order record to the XLSX File.
     *
     * @param order Order record to add
     * @param numExistingRecords Number of existing order records.
     */
    public void writeToXlsx(Order order, int numExistingRecords) {
        serializeRecord(this.orderXlsx, order.toXlsx(), numExistingRecords);
    }

    /**
     * Updates an Order record in the XLSX File.
     *
     * @param order Order record to update.
     */
    public void updateXlsx(Order order) {
        serializeUpdate(this.orderXlsx, order.toXlsx(), order.getOrderID());
    }
}
