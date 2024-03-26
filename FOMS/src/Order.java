public class Order {
    public enum Status{NEW, READY, COMPLETED, CANCELLED}

    private String orderID;
    private String branch;
    private Item[] items;
    private String[] customisation;
    private boolean takeaway;
    private String status;
    private String paymentMethod;

    public Order(String orderID, String branch, Item[] items, String[] customisation, boolean takeaway, String paymentMethod){
        this.orderID = orderID;
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.status = setStatus(Status.NEW);
        this.paymentMethod = paymentMethod;
    }

    public void pay(String paymentMethod){
        setStatus(Status.READY);
    }

    public void printReceipt(){
        System.out.println("Your order ID is " + orderID);
        System.out.println("Your order is: ");
        for (Item item : items){
            System.out.println("- " + item.getName());
        }
        System.out.println("Your customisations are: ");
        for (String customization : customisation){
            System.out.println("- " + customization);
        }
        if (takeaway == true){
            System.out.println("You chose to takeaway");
        }
        else{
            System.out.println("You chose to dine in");
        }
        System.out.println("Thank you for shopping at " + branch);
    }

    public void getOrderById(String orderID) {
        
    }

    public void processOrder() {
        if (items == null || items.length == 0) {
            System.out.println("Error: Cannot process order without selecting any items.");
            return;
        }

        // Process the order if items are selected
        setStatus(Status.READY);
        System.out.println("Order processed successfully.");
    }

    public String getStatus(){
        return status;
    }

    protected String setStatus(Status status){
        this.status = status.toString();
        return this.status;
    }

    public void collectOrder(){
        if (getStatus().equals(Status.READY.toString())){
            setStatus(Status.COMPLETED);
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