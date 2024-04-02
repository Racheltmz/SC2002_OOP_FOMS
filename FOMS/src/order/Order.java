package order;

import menu.MenuItem;

import java.util.ArrayList;

// TODO: STRUCT: PRINT RECEIPT UNDER VIEW
// TODO: UNIQUE AND AUTO INCREMENTED ID
// TODO: STRUCT: Implement pay method as a view for pay to print out paid message
// TODO: Merge the customer order functions (by gwen) with the staff order functions (in enhancement branch)

// Order details
public class Order {
    // Attributes
    private String orderID;
    private String branch;
    private ArrayList<MenuItem> items;
    private String[] customisation;
    private boolean takeaway;
    private OrderStatus status;
    private String paymentMethod;

    // Constructor
    public Order(String orderID, String branch, ArrayList<MenuItem> items, String[] customisation, boolean takeaway,
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

    public ArrayList<MenuItem> getItems() {
        return this.items;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Functionalities
    // Customer makes payment
    public void pay(String paymentMethod) {
        this.setStatus(OrderStatus.NEW);
    }

    // Receipt information
    public void printReceipt() {
        System.out.println("Your order ID is " + this.orderID);
        System.out.println("Your order is: ");
        for (MenuItem item : this.items) {
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
        for (MenuItem item : this.items) {
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
