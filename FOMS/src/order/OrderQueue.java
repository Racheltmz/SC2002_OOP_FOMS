package order;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import utils.InputScanner;
import io.OrderXlsxHelper;

import static order.OrderView.printOrderDetails;
import static utils.ValidateHelper.validateString;

/**
 * Records of all orders made by customers
 */
// TODO: refactor design
public class OrderQueue {
    // Attributes
    private final ArrayList<Order> orders;
    private static OrderQueue ordersSingleton = null;

    // Constructor
    public OrderQueue() {
        OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();
        this.orders = orderXlsxHelper.readFromXlsx();
    }

    public static OrderQueue getInstance() {
        if (ordersSingleton == null) {
            ordersSingleton = new OrderQueue();
        }
        return ordersSingleton;
    }

    /**
     * Check if the queue has any orders
     *
     * @return boolean
     */
    public ArrayList<Order> ordersInBranch(String branch) {
        ArrayList<Order> filteredOrders = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getBranch().equals(branch)) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }


    // Get new orders in the branch
    public ArrayList<Order> getNewOrdersBranch(String branch) {
        ArrayList<Order> branchOrders = ordersInBranch(branch);
        ArrayList<Order> newOrders = new ArrayList<>();
        if (!branchOrders.isEmpty()) {
            for (Order order : branchOrders) {
                if (Objects.equals(order.getStatus(), OrderStatus.NEW) && order.getBranch().equals(branch)) {
                    newOrders.add(order);
                }
            }
        }
        return newOrders;
    }

    // Get order by ID
    public Order getOrderById(String branch) {
        if (!ordersInBranch(branch).isEmpty()) {
            // Iterate until user enters a valid id
            while (true) {
                try {
                    // Get user's input
                    String orderID = validateString("Enter orderID (enter quit to exit): ");
                    if (orderID.equalsIgnoreCase("quit") || orderID.isBlank()) {
                        return null;
                    }
                    // Return order object if it can be found
                    for (Order order : getNewOrdersBranch(branch)) {
                        if (Objects.equals(order.getOrderID(), orderID))
                            return order;
                    }
                    throw new ItemNotFoundException("Please retry, invalid orderID.");
                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    // Display a specific order
    public void displayOrder(String branch) {
        if (!ordersInBranch(branch).isEmpty()) {
            Order order = getOrderById(branch);
            if (order != null) {
                printOrderDetails(order);
            }
        }
    }

    // Display new orders
    public void displayNewOrders(String branch) {
        ArrayList<Order> newOrders = this.getNewOrdersBranch(branch);
        if (newOrders.isEmpty()) {
            System.out.println("No new orders exist.");
        } else {
            System.out.println("New orders in the queue:");
            for (Order order : newOrders) {
                printOrderDetails(order);
            }
        }
    }

    // Get order status by order ID
    public void getStatusById(String branch) {
        if (!ordersInBranch(branch).isEmpty()) {
            Order order = getOrderById(branch);
            if (order == null) {
                return;
            }
            System.out.printf("Status of Order ID: %s is %s\n", order.getOrderID(), order.getStatus());
        }
    }

    // Add order
    public void addOrder(Order order) {
        int numExistingOrders = this.orders.size();
        this.orders.add(order);
        OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();
        orderXlsxHelper.writeToXlsx(order, numExistingOrders);
    }

    // Update order status to ready when food is ready or when customer collects order
    public void updateStatusToReady(String branch) {
        if (!ordersInBranch(branch).isEmpty()) {
            OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();
            // Get order
            Order order = getOrderById(branch);
            // Set timer
            Timer timer = new Timer();
            OrderTimerTask orderTask = new OrderTimerTask(timer, order);
            int seconds = 20;
            order.setStatus(OrderStatus.READY);
            timer.schedule(orderTask, seconds * 1000);
            orderXlsxHelper.updateXlsx(order);
            System.out.println("Order status updated from NEW to READY for order ID: " + order.getOrderID());
        }
    }
}
