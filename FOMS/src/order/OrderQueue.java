package order;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import exceptions.EmptyListException;
import exceptions.ItemNotFoundException;
import utils.InputScanner;

/**
 * Records of all orders made by customers
 */
public class OrderQueue {
    // Attributes
    private final ArrayList<Order> orders;
    private static OrderQueue ordersSingleton = null;

    // Constructor
    public OrderQueue() {
        this.orders = new ArrayList<>();
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
                return false;
            else
                throw new EmptyListException("Order queue is empty.");
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    // Get order by ID
    public Order getOrderById() {
        InputScanner sc = InputScanner.getInstance();
        if (ordersExist()) {
            // Iterate until user enters a valid id
            while (true) {
                try {
                    // Get user's input
                    System.out.println("Enter orderID: ");
                    String orderID = sc.next();
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
            order.printOrderDetails();
        }
    }
    
    // Display all orders
    public void displayAllOrders(){
        if (!this.orders.isEmpty()){
            System.out.println("All Orders:");
            for (Order order : this.orders){
                OrderView.printOrderDetails(order);
            }
        }
        else{
            System.out.println("No orders to display.");
        }
    }

    // Display new orders
    public void displayNewOrders(String branch) {
        if (ordersExist()) {
            System.out.println("Orders in the queue:");
            for (Order order : this.orders) {
                if (Objects.equals(order.getStatus(), OrderStatus.NEW) && Objects.equals(order.getBranch(), branch)) {
                    OrderView.printOrderDetails(order);
                }
            }
        }
    }

    // Get order status by order ID
    public void getStatusById() {
        if (ordersExist()) {
            Order order = getOrderById();
            System.out.printf("Status of Order ID: %s is %s", order.getOrderID(), order.getStatus());
        }
    }

    // Add order
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    // Update order status to ready when food is ready or when customer collects order
    public void updateStatus(OrderStatus valStatus, OrderStatus newStatus) {
        if (ordersExist()) {
            Order order = getOrderById();
            if (order.getStatus().equals(valStatus))
                order.setStatus(newStatus);
            System.out.println("Order status updated successfully.");
        }
    }

    // Remove order
    public void rmvOrder() {
        if (ordersExist()) {
            this.orders.remove(getOrderById());
            System.out.println("Order removed successfully.");
        }
    }
}
