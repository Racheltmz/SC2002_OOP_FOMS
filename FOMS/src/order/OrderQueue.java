package order;

import java.util.ArrayList;
import java.util.Objects;

import utils.InputScanner;

import exceptions.EmptyListException;

import static utils.InputScanner.getInstance;

// TODO: Create views accordingly (DONE)
// TODO: Merge the customer order functions (by gwen) with the staff order functions (in enhancement branch)
// Records of all orders made by customers (DONE)
public class OrderQueue {
    // Attributes
    private ArrayList<Order> orders;

    // Constructor
    public OrderQueue() {
        this.orders = new ArrayList<Order>();
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
            System.out.println("Order removed.");
        } catch (EmptyListException emptyListException) {
            System.out.println("No orders to remove.");
        }
    }

    // Get order by ID
    public Order getOrderById() throws EmptyListException {
        InputScanner sc = getInstance();
        if (!this.orders.isEmpty()) {
                while(true){
                    // Get user's input
                    System.out.println("Enter orderID: ");
                    String orderID = sc.next();

                    // Return order object if found
                    for (Order order : this.orders) {
                        if (Objects.equals(order.getOrderID(), orderID))
                            return order;
                    } 
                    System.out.println("Please retry, invalid orderID.");
                }
                
        } else {
            throw new EmptyListException("No orders to display.");
        }
    }

    // Display a specific order
    public void displayOrder() {
        try {
            Order order = this.getOrderById();
            OrderView.printOrderDetails(order);
        } catch (EmptyListException emptyListException) {
            System.out.println("There has to be an order before this option can be used.");
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
        if (!this.orders.isEmpty()) {
            System.out.println("Orders in the queue:");
            for (Order order : this.orders) {
                if (Objects.equals(order.getStatus(), OrderStatus.NEW) && Objects.equals(order.getBranch(), branch)) {
                    OrderView.printOrderDetails(order);
                }
            }
        } else {
            System.out.println("No orders to display.");
        }
    }

    // TODO: Implement for test case 8, 18
    // Get order status by order ID
    public OrderStatus getStatusById() throws EmptyListException {
        InputScanner sc = getInstance();
        if (!this.orders.isEmpty()){
            while(true){
                // Get user's input
                System.out.println("Enter orderID: ");
                String orderID = sc.next();

                // Return status if order found
                for (Order order : this.orders){
                    if (Objects.equals(order.getOrderID(),orderID))
                        return order.getStatus();
                }
                System.out.println("Invalid orderID. Please retry.");
            }
        }
        else{
            throw new EmptyListException("No orders to display.");
        }
    }

    // Update order status to ready when food is ready or when customer collects order
    public void updateStatus(OrderStatus valStatus, OrderStatus newStatus) {
        try {
            Order order = this.getOrderById();
            if (order.getStatus().equals(valStatus))
                order.setStatus(newStatus);
            System.out.println("Order status updated from " + valStatus + " to " + newStatus);
        } catch (EmptyListException emptyListException) {
            System.out.println("There has to be an order before this option can be used.");
        }
    }

    // Process order placement
    public void placeOrder(Order order){
        order.placeOrder();
    }
}
