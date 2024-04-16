package order;

import menu.MenuItem;
import payment.Payment;
import payment.PaymentView;
import payment.PaymentDirectory;

import java.util.ArrayList;
import java.util.Arrays;

import static payment.PaymentDirectory.readPaymentMethods;
import static utils.ValidateHelper.validateIntRange;

// Order details
public class Order {
    // Attributes
    private String orderID;
    private String branch;
    private ArrayList<MenuItem> items;
    private String[] customisation;
    private boolean takeaway;
    private OrderStatus status;
    private Payment payment;
    private PaymentDirectory paymentDirectory;

    // Constructor
    public Order(String branch, ArrayList<MenuItem> items, String[] customisation, boolean takeaway,
            Payment payment) {
        this.orderID = UniqueIdGenerator.generateUniqueID(takeaway);
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.status = OrderStatus.NEW;
        this.payment = payment;
    }

    // Serialization to XLSX
    public String[] toXlsx() {
        return new String[] { orderID, branch, String.valueOf(items), Arrays.toString(customisation),
                String.valueOf(takeaway), String.valueOf(status), payment.getPaymentMethod() };
    }

    // Getters and Setters
    public String getOrderID() {
        return this.orderID;
    }

    public String getBranch() {
        return this.branch;
    }

    public String[] getCustomisation() {
        return this.customisation;
    }

    public boolean isTakeaway() {
        return this.takeaway;
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

    public void setPaymentMtd(Payment payment) { this.payment = payment; }

    // Functionalities
    // Place order
    public void placeOrder() {
        if (payment != null) {
            System.out.println("Order placed successfully.");
            setStatus(OrderStatus.NEW);
        } else {
            System.out.println("Payment method not set. Cannot place order.");
        }
    }

    // Calculate total price
    public double calculateTotal() {
        double total = 0.00;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // Display payment methods and customer makes payment
    public static Payment pay() {
        // Initialise directory
        PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();
        ArrayList<String> paymentMethodList = readPaymentMethods();

        // Display payment methods
        if (paymentDirectory.printPaymentMethods())
        {
            int choice = validateIntRange("How would you like to pay?", 1, paymentMethodList.size());
            String paymentOption = paymentMethodList.get(choice - 1);
            Payment paymentOpt = new Payment(paymentOption);
            return paymentOpt;
        }
        System.out.println("Please approach staff for assistance.");
        return null;
    }

    // Process the order
    public void processOrder() {
        if (items == null) {
            System.out.println("Error: Cannot process order without selecting any items.");
            return;
        }
        setStatus(OrderStatus.READY);
        System.out.println("Order processed successfully.");
    }

    // Receipt information
    public void printReceipt() {
        OrderView.printReceipt(this);
    }

}