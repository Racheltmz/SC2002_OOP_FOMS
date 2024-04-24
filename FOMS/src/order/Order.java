package order;

import io.OrderXlsxHelper;
import menu.MenuItem;
import payment.Payment;
import payment.PaymentDirectory;
import io.IXlsxSerializable;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Objects;

import static payment.PaymentView.displayPaymentMethods;
import static utils.ValidateHelper.validateIntRange;

// Order details
public class Order implements IXlsxSerializable {
    // Attributes
    private String orderID;
    private String branch;
    private ArrayList<MenuItem> items;
    private ArrayList<String> customisation;
    private boolean takeaway;
    private double totalAmount;
    private String payment;
    private OrderStatus status;

    // Constructor
    public Order(String orderID, String branch, ArrayList<MenuItem> items, ArrayList <String> customisation, boolean takeaway,
                 double totalAmount, String payment) {
        this.orderID = orderID;
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.totalAmount = totalAmount;
        this.payment = payment;
        this.status = OrderStatus.NEW;
    }
    public Order(String branch, ArrayList<MenuItem> items, ArrayList <String> customisation, boolean takeaway, String payment) {
        this.orderID = UniqueIdGenerator.generateUniqueID(takeaway);
        String newOrderID;
        do {
            newOrderID = UniqueIdGenerator.generateUniqueID(takeaway);
        } while (isDuplicateOrderID(newOrderID)); // Check for duplicate IDs
        this.orderID = newOrderID;
        this.branch = branch;
        this.items = items;
        this.customisation = customisation;
        this.takeaway = takeaway;
        this.totalAmount = this.calculateTotal();
        this.payment = payment;
        this.status = OrderStatus.NEW;
    }

    private boolean isDuplicateOrderID(String orderID) {
        OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();
        // Retrieve existing order IDs from the Excel file
        ArrayList<Order> existingOrders = orderXlsxHelper.readFromXlsx(); // Implement this method
        for (Order order : existingOrders) {
            if (Objects.equals(order.getOrderID(), orderID)) {
                return true;
            }
        }
        return false;
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

        return new String[] { orderID, branch, itemsString, customisationsString,
                String.valueOf(takeaway), String.valueOf(totalAmount), payment, String.valueOf(status) };
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
        ArrayList<Payment> paymentList = paymentDirectory.getPaymentMethods();

        // If there are existing payment methods, get customer to choose one
        if (!paymentList.isEmpty()) {
            // Display payment methods
            displayPaymentMethods(paymentList);

            // Get customer's choice
            int choice = validateIntRange("How would you like to pay?", 1, paymentList.size());
            Payment payment = paymentList.get(choice - 1);
            payment.getDetails();
            System.out.println("Payment by " + payment.getPaymentMethod() + " is successful.");
            return payment;
        }

        // Else notify them to approach staff
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