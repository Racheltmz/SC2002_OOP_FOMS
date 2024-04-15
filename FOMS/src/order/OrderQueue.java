package order;

import java.util.ArrayList;
import java.util.Objects;

import exceptions.EmptyListException;
import utils.InputScanner;

// TODO: Create views accordingly
// TODO: Merge the customer order functions (by gwen) with the staff order functions (in enhancement branch)
// Records of all orders made by customers
public class OrderQueue {
    // Attributes
    private ArrayList<Order> orders;
    private static OrderQueue ordersSingleton = null;

    // Constructor
    public OrderQueue() {
        this.orders = new ArrayList<Order>();
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

    // Add order
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    // Remove order
    public void rmvOrder() {
        try {
            this.orders.remove(this.getOrderById());
        } catch (EmptyListException emptyListException) {
            System.out.println("There are no orders to remove.");
        }
    }

    // Get order by ID
    public Order getOrderById() throws EmptyListException {
        InputScanner sc = InputScanner.getInstance();
        if (!this.orders.isEmpty()) {
            // Else iterate until user enters a valid id
            while (true) {
                // Get user's input
                System.out.println("Enter orderID: ");
                String orderID = sc.next();
                // Return order object if it can be found
                for (Order order : this.orders) {
                    if (Objects.equals(order.getOrderID(), orderID))
                        return order;
                }
                System.out.println("Please retry, invalid orderID.");
            }
        } else {
            throw new EmptyListException("Order queue is empty. No orders to display.");
        }
    }

    // Display a specific order
    public void displayOrder() {
        try {
            Order order = this.getOrderById();
            order.printOrderDetails();
        } catch (EmptyListException emptyListException) {
            System.out.println(emptyListException.getMessage());
        }
    }

     // Display orders
     public void displayNewOrders(String branch) {
        try {
            if (!this.orders.isEmpty()) {
                System.out.println("Orders in the queue:");
                for (Order order : this.orders) {
                    if (Objects.equals(order.getStatus(), OrderStatus.NEW) && Objects.equals(order.getBranch(), branch)) {
                        order.printOrderDetails();
                    }
                }
            } else {
                throw new EmptyListException("Queue is empty. No orders to display.");
            }
        } catch (EmptyListException emptyListException) {
            System.out.println(emptyListException.getMessage());
        }
    }

    // TODO: Implement for test case 8, 18
 // Get order status by order ID
 public void getStatusById() {
    try {
        Order order = this.getOrderById();
        System.out.printf("Status of Order ID: %s is %s", order.getOrderID(), order.getStatus());
    } catch (EmptyListException emptyListException) {
        System.out.println(emptyListException.getMessage());
    }
}
   // Update order status
   public void updateStatus(OrderStatus valStatus, OrderStatus newStatus) {
    try {
        Order order = this.getOrderById();
        if (order.getStatus().equals(valStatus))
            order.setStatus(newStatus);
        System.out.println("Order status update processed successfully.");
    } catch (EmptyListException emptyListException) {
        System.out.println(emptyListException.getMessage());
    }
}
}