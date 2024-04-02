import java.util.Random;

public class Order {
    public enum Status{ NEW, READY, COMPLETED, CANCELLED }
    private int orderID;
    private String branch;
    private Item[] items;
    private String[] customisation;
    private boolean takeaway;
    private Status status;
    private String paymentMethod;

    public Order(String branch, Item[] items, String[] customisation, boolean takeaway, String paymentMethod){
        this.orderID = generateRandomOrderID();
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.status = Status.NEW;
        this.paymentMethod = paymentMethod;
    }

    private int generateRandomOrderID() {
        Random random = new Random();
        return random.nextInt(1000);
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

    public int getOrderId() {
        return orderID;
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
        return status.toString();
    }

    protected void setStatus(Status status){
        this.status = status;
    }

    public void collectOrder(){
        if (getStatus().equals(Status.READY.toString())){
            setStatus(Status.COMPLETED);
        }
    }
}