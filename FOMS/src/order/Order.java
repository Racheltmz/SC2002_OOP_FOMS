package order;

import menu.MenuItem;
import payment.Payment;
import payment.PaymentDirectory;
import io.IXlsxSerializable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.text.DecimalFormat;

import static payment.PaymentDirectory.readPaymentMethods;
import static utils.ValidateHelper.validateIntRange;

// Order details
public class Order implements IXlsxSerializable {
    // Attributes
    private String orderID;
    private String branch;
    private ArrayList<MenuItem> items;
    private ArrayList<String> customisation;
    private boolean takeaway;
    private OrderStatus status;
    private Payment payment;

    // Constructor
    public Order(String branch, ArrayList<MenuItem> items, ArrayList <String> customisation, boolean takeaway,
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
        ArrayList<String> itemNames = new ArrayList<>();
        for (MenuItem item : items) {
            itemNames.add(item.getName());
        }
        // Convert items ArrayList to a comma-separated string
        String itemsString = convertArrayListToString(itemNames);

        // Convert customisations ArrayList to a comma-separated string
        String customisationsString = convertArrayListToString(customisation);

        // Convert payment method to string
        String paymentMethod = (payment.getPaymentMethod());

        return new String[] { orderID, branch, itemsString, customisationsString,
                String.valueOf(takeaway), String.valueOf(status), paymentMethod };
    }

    // Getters and Setters
    public String getOrderID() {
        return this.orderID;
    }

    public String getBranch() {
        return this.branch;
    }

    public ArrayList<String> getCustomisation() { return this.customisation; }

    public boolean isTakeaway() {
        return this.takeaway;
    }

    public ArrayList<MenuItem> getItems() {
        return this.items;
    }

    public OrderStatus getStatus() { return this.status; }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPaymentMtd(Payment payment) { this.payment = payment; }

    // Functionalities
    // Place order
    public void placeOrder() {
        if (payment != null) {
            System.out.println("Order placed successfully.\n");
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
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(total));
    }

    // Display payment methods and customer makes payment
    public static Payment pay() {
        // Initialise directory
        PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();
        ArrayList<String> paymentMethodList = readPaymentMethods();

        // Display payment methods
        if (paymentDirectory.printPaymentMethods())
        {
            int choice = validateIntRange("How would you like to pay?", 1, paymentMethodList.size(), true);
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

    private String convertArrayListToString(ArrayList<?> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            sb.append(arrayList.get(i));
            if (i < arrayList.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}