package order;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import utils.InputScanner;
import io.OrderXlsxHelper;

import static utils.ValidateHelper.validateString;

/**
 * Records of all orders made by customers
 */
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

    // Get all orders
    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    /**
     * Check if the queue has any orders
     *
     * @return boolean
     */
    public boolean ordersExist() {
        try {
            if (this.orders.isEmpty())
                throw new EmptyListException("Order queue is empty.");
            else
                return true;
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Get order by ID
    public Order getOrderById() {
        InputScanner sc = InputScanner.getInstance();
        if (ordersExist()) {
            // Iterate until user enters a valid id
            while (true) {
                try {
                    // Get user's input
                    String orderID = validateString("Enter orderID (enter quit to exit): ");
                    if (orderID.equalsIgnoreCase("quit")) {
                        break;
                    }
                    // Return order object if it can be found
                    for (Order order : this.orders) {
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
    public void displayOrder() {
        if (ordersExist()) {
            Order order = getOrderById();
            if (order != null) {
                OrderView.printOrderDetails(order);
            }
        }
    }

    // Display all orders
    public void displayAllOrders() {
        if (ordersExist()) {
            System.out.println("All Orders:");
            for (Order order : this.orders) {
                OrderView.printOrderDetails(order);
            }
        }
    }

    // Display new orders
    public void displayNewOrders(String branch) {
        if (ordersExist()) {
            boolean printedHeader = false;
            for (Order order : this.orders) {
                if (Objects.equals(order.getStatus(), OrderStatus.NEW) && Objects.equals(order.getBranch(), branch)) {
                    if (!printedHeader) {
                        System.out.println("New orders in the queue:");
                        printedHeader = true;
                    }
                    OrderView.printOrderDetails(order);
                }
            }
            if (!printedHeader)
            {
                System.out.println("No new orders exist.");
            }
        }
    }

    // Get order status by order ID
    public void getStatusById() {
        if (ordersExist()) {
            Order order = getOrderById();
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

    // Process order placement
//    public void placeOrder(Order order) {
//        order.placeOrder();
//    }

    // Update order status to ready when food is ready or when customer collects order
    public void updateStatusToReady() {
        if (ordersExist()) {
            OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();
            // Get order
            Order order = getOrderById();
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

    // Remove order
//    public void rmvOrder() {
//        if (ordersExist()) {
//            this.orders.remove(getOrderById());
//            System.out.println("Order removed successfully.");
//        }
//    }
}
