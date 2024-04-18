package io;

import order.Order;
import order.OrderQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderXlsxHelper extends BaseXlsxHelper {
    /**
     * Path to Order XLSX File in the data folder. Defaults to order_list.xlsx.
     */
    private final String orderXlsx = "order_lists.xlsx";

    /**
     * Headers for order.
     */
    private final String[] header = {"id", "name", "price", "branch", "category", "description"};

    /**
     * Default Constructor.
     */
    public OrderXlsxHelper() {}

    /**
     * Singleton instance of order xlsx helper.
     */
    private static OrderXlsxHelper orderInstance;

    /**
     * Gets the singleton instance of OrderXlsxHelper that reads from menu.xlsx
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
        OrderQueue orderQueue = OrderQueue.getInstance();

        // Deserialize records
        List<String[]> XlsxData = deserializeRecords(this.orderXlsx, 5, 1);
        if (XlsxData.isEmpty()) {
            serializeHeader(this.orderXlsx, this.header);
            return orders;
        }

        // Add order
        XlsxData.forEach((data) -> {
            String orderID = data[0];
            String branch = data[1];


//            orders.add(new Order(orderID, branch, ));
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

    /**
     * Deletes an Order record in the XLSX File.
     *
     * @param id ID of Order record to delete
     */
    public void removeXlsx(UUID id) {
        deleteRecord(this.orderXlsx, id);
    }
}
