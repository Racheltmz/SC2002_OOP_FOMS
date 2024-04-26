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

/**
 * Order class represents an Order placed by a customer.
 */
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

    /**
     * Constructs an Order.
     *
     * @param orderID This Order's orderID.
     * @param branch The Branch of this Order.
     * @param customisation This Order's customisations.
     * @param items The existing MenuItems in this Order.
     * @param payment The payment method used for this Order.
     * @param takeaway The pickup option of this Order.
     * @param totalAmount The total amount of the MenuItems in this Order.
     */
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

    /**
     * Constructs an Order.
     *
     * @param branch The Branch of this Order.
     * @param customisation This Order's customisations.
     * @param items The existing MenuItems in this Order.
     * @param payment The payment method used for this Order.
     * @param takeaway The pickup option of this Order.
     */
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

    /**
     * Checks if the generated ID already exists in the Excel sheet to prevent duplicate orderIDs.
     *
     * @return true if it is a duplicate ID, false if not.
     */
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

    /**
     * Convert Order details to String for serialisation.
     * @return String array of this Order's details.
     */
    public String[] toXlsx() {
        ArrayList<String> itemNames = new ArrayList<>();
        for (MenuItem item : items) {
            itemNames.add(item.getName());
        }
        // Convert items ArrayList to a comma-separated string
        String itemsString = convertArrayListToString(itemNames);

        // Convert customisations ArrayList to a comma-separated string
        String customisationsString = convertArrayListToString(customisation);
        if (customisationsString.isEmpty())
            customisationsString = "NULL";
        return new String[] { orderID, branch, itemsString, customisationsString,
                String.valueOf(takeaway), String.valueOf(totalAmount), payment, String.valueOf(status) };
    }

    /**
     * Get orderID of this Order.
     *
     * @return This Order's orderID.
     */
    public String getOrderID() {
        return this.orderID;
    }

    /**
     * Get Branch in which this Order exists.
     *
     * @return The name of the Branch.
     */
    public String getBranch() {
        return this.branch;
    }

    /**
     * Get customisations of this Order.
     *
     * @return A String ArrayList of the customisations of this Order.
     */
    public ArrayList<String> getCustomisation() { return this.customisation; }

    /**
     * Get the pickup option of this Order.
     *
     * @return true if the option is Takeaway, and false otherwise.
     */
    public boolean isTakeaway() {
        return this.takeaway;
    }

    /**
     * Get the MenuItems in this Order.
     *
     * @return ArrayList of MenuItems in this Order.
     */
    public ArrayList<MenuItem> getItems() {
        return this.items;
    }

    /**
     * Get the OrderStatus of this Order.
     *
     * @return Status of this Order.
     */
    public OrderStatus getStatus() { return this.status; }

    /**
     * Set the OrderStatus of this Order.
     *
     * @param status Status of this Order.
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Verifies that payment method has been selected, and processes this Order if so.
     * OrderStatus of this Order is initialised to NEW.
     */
    public void placeOrder() {
        if (payment != null) {
            System.out.println("Order placed successfully.\n");
            setStatus(OrderStatus.NEW);
        } else {
            System.out.println("Payment method not set. Cannot place order.");
        }
    }

    /**
     * Calculates the total amount of the MenuItems in this Order.
     *
     * @return Total amount of this Order in 2 decimal point form.
     */
    public double calculateTotal() {
        double total = 0.00;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(total));
    }

    /**
     * Prompts customer to select which payment method to use after displaying available payment methods.
     *
     * @return Customer's selected Payment method.
     */
    public static Payment pay() {
        // Initialise directory
        PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();
        ArrayList<Payment> paymentList = paymentDirectory.getPaymentMethods();

        // If there are existing payment methods, get customer to choose one
        if (!paymentList.isEmpty()) {
            // Display payment methods
            displayPaymentMethods(paymentList);

            // Get customer's choice
            int choice = validateIntRange("How would you like to pay?: ", 1, paymentList.size());
            Payment payment = paymentList.get(choice - 1);
            payment.getDetails();
            System.out.println("Payment by " + payment.getPaymentMethod() + " is successful.");
            return payment;
        }

        // Else notify them to approach staff
        System.out.println("Please approach staff for assistance.");
        return null;
    }

    /**
     * Prints receipt entailing the details of this Order.
     */
    public void printReceipt() {
        OrderView.printReceipt(this);
    }

    /**
     * Converts an ArrayList of Strings into a comma-separated value in String form.
     *
     * @return String of comma-separated values.
     */
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