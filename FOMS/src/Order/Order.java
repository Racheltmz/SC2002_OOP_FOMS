package Order;

import java.util.HashMap;
import java.util.Map;

public class Order {
    public enum Status {
        NEW, READY, COMPLETED, CANCELLED
    }

    private static Map<String, Order> orderMap = new HashMap<>();

    private String orderID;
    private String branch;
    private Item[] items;
    private String[] customisation;
    private boolean takeaway;
    private String status;
    private String paymentMethod;

    public Order(String orderID, String branch, Item[] items, String[] customisation, boolean takeaway,
            String paymentMethod) {
        this.orderID = orderID;
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.status = setStatus(Status.NEW);
        this.paymentMethod = paymentMethod;
        orderMap.put(orderID, this);
    }

    public void pay(String paymentMethod) {
        this.setStatus(Status.NEW);
    }

    public void printReceipt() {
        System.out.println("Your order ID is " + orderID);
        System.out.println("Your order is: ");
        for (Item item : this.items) {
            System.out.println("- " + item.getName());
        }
        System.out.println("Your customisations are: ");
        for (String customization : this.customisation) {
            System.out.println("- " + customization);
        }
        if (this.takeaway == true) {
            System.out.println("You chose to takeaway");
        } else {
            System.out.println("You chose to dine in");
        }
        System.out.println("Thank you for shopping at " + branch);
    }

    public static Order getOrderById(String orderID) {
        return orderMap.get(orderID);
    }

    public void processOrder(String orderID) {
        Order order = getOrderById(orderID);
        if (order.items == null || order.items.length == 0) {
            System.out.println("Error: Cannot process order without selecting any items.");
            return;
        }

        // Process the order if items are selected
        order.setStatus(Status.READY);
        System.out.println("Order processed successfully.");
    }

    public String getStatus() {
        return this.status.toString();
    }

    protected String setStatus(Status status) {
        this.status = status.toString();
        return this.status;
    }

    public String getBranch() {
        return this.branch;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public void collectOrder() {
        if (this.getStatus().equals(Status.READY.toString())) {
            this.setStatus(Status.COMPLETED);
        }
    }

    public static void main(String[] args) {
        // Test Case 20: Attempt to process an order without selecting any items
        Order orderWithoutItems = new Order("789", "Branch C", new Item[] {}, new String[] {}, false, "Cash");
        orderWithoutItems.processOrder("789"); // This should display the error message

        // Test Case 21: Process an order with items
        Order orderWithItems = new Order("456", "Branch B", new Item[] { new Item("Pizza") }, new String[] {}, false,
                "Card");
        orderWithItems.processOrder("456"); // This should process the order successfully
    }
}

// delete when item class is made
class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}