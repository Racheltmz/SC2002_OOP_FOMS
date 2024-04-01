package Order;

import Menu.Item;

import java.util.ArrayList;

// Information about order
public class Order {
    private String orderID;
    private String branch;
    private ArrayList<Item> items;
    private String[] customisation;
    private boolean takeaway;
    private OrderStatus status;
    private String paymentMethod;

    public Order(String orderID, String branch, ArrayList<Item> items, String[] customisation, boolean takeaway,
            String paymentMethod) {
        this.orderID = orderID;
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.status = OrderStatus.NEW;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public String getOrderID() {
        return this.orderID;
    }

    public String getBranch() {
        return this.branch;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /* Details for customers */
    // Customer makes payment
    public void pay(String paymentMethod) {
        this.setStatus(OrderStatus.NEW);
    }

    // Receipt information
    public void printReceipt() {
        System.out.println("Your order ID is " + this.orderID);
        System.out.println("Your order is: ");
        for (Item item : this.items) {
            System.out.println("- " + item.getName());
        }
        System.out.println("Your customisations are: ");
        for (String customization : this.customisation) {
            System.out.println("- " + customization);
        }
        if (this.takeaway) {
            System.out.println("You chose to takeaway");
        } else {
            System.out.println("You chose to dine in");
        }
        System.out.println("Thank you for shopping at " + branch);
    }

    // Print order details
    public void printOrderDetails() {
        System.out.println("Your order ID is " + this.orderID);
        System.out.println("Items:");
        for (Item item : this.items) {
            System.out.println("- " + item.getName());
        }
        System.out.println("Customisations: ");
        for (String customization : this.customisation) {
            System.out.println("- " + customization);
        }
        System.out.print("Takeaway or Dine In: ");
        if (this.takeaway) {
            System.out.println("Takeaway");
        } else {
            System.out.println("Dine In");
        }
    }
}
