package Order;

import java.util.ArrayList;
import java.util.Objects;

import Initialisation.InputScanner;

import Exceptions.EmptyListException;

import static Initialisation.InputScanner.getInstance;

// Manage orders already in queue
public class OrderQueue {
    private ArrayList<Order> orders;

    // Instantiate Order Queue
    public OrderQueue() {
        this.orders = new ArrayList<Order>();
    }

    // Get all orders
    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    /* ORDER PURPOSES */
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

    /* ORDER INFORMATION PURPOSES */
    // Get order by ID
    public Order getOrderById() throws EmptyListException {
        InputScanner sc = getInstance();
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
            System.out.println("There has to be an order before this option can be used.");
        }
    }

    // Display orders
    public void displayNewOrders(String branch) {
        if (!this.orders.isEmpty()) {
            System.out.println("Orders in the queue:");
            for (Order order : this.orders) {
                if (Objects.equals(order.getStatus(), OrderStatus.NEW) && Objects.equals(order.getBranch(), branch)) {
                    order.printOrderDetails();
                }
            }
        } else {
            System.out.println("Queue is empty. No orders to display.");
        }
    }

    /* ORDER STATUS PURPOSES */
    // TODO: Implement for test case 8, 18
    public void getStatusById() {
        try {
            Order order = this.getOrderById();
            System.out.printf("Status of Order ID: %s is %s", order.getOrderID(), order.getStatus());
        } catch (EmptyListException emptyListException) {
            System.out.println("There has to be an order before this option can be used.");
        }
    }

    // Update order status to ready when food is ready or when customer collects order
    public void updateStatus(OrderStatus valStatus, OrderStatus newStatus) {
        try {
            Order order = this.getOrderById();
            if (order.getStatus().equals(valStatus))
                order.setStatus(newStatus);
            System.out.println("Order status update processed successfully.");
        } catch (EmptyListException emptyListException) {
            System.out.println("There has to be an order before this option can be used.");
        }
    }
}
