package Order;

// Information about order
public class Order {
    public enum Status { NEW, READY, COMPLETED, CANCELLED }

    private String orderID;
    private String branch;
    private Item[] items;
    private String[] customisation;
    private boolean takeaway;
    private Status status;
    private String paymentMethod;

    public Order(String orderID, String branch, Item[] items, String[] customisation, boolean takeaway, String paymentMethod) {
        this.orderID = orderID;
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.status = Status.NEW;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public String getOrderID() {
        return this.orderID;
    }

    public String getBranch() {
        return this.branch;
    }

    public Item[] getItems() {
        return this.items;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /* Details for customers */
    // Customer makes payment
   public void pay(String paymentMethod) {
        this.setStatus(Status.NEW);
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