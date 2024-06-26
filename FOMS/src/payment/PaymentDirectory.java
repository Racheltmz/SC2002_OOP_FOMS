package payment;

import io.PaymentXlsxHelper;

import java.util.ArrayList;

import static payment.PaymentView.displayPaymentMethods;
import static utils.ValidateHelper.validateIntRange;
import static utils.ValidateHelper.validateString;

/**
 * Records of payment methods. Created as a singleton.
 */
public class PaymentDirectory {
    // Attributes
    private final ArrayList<Payment> paymentDirectory;
    private static PaymentDirectory paymentSingleton = null;

    /**
     * Singleton constructor that reads all Payment method records from the storage file on initialisation.
     */
    private PaymentDirectory() {
        PaymentXlsxHelper paymentTxtHelper = new PaymentXlsxHelper();
        this.paymentDirectory = paymentTxtHelper.readFromXlsx();
    }

    /**
     * Gets the instance for the PaymentDirectory records.
     *
     * @return PaymentDirectory instance.
     */
    public static PaymentDirectory getInstance() {
        if (paymentSingleton == null) {
            paymentSingleton = new PaymentDirectory();
        }
        return paymentSingleton;
    }

    /**
     * Gets all the existing Payment methods stored in this PaymentDirectory.
     *
     * @return An ArrayList of the existing Payment methods.
     */
    public ArrayList<Payment> getPaymentMethods() {
        return this.paymentDirectory;
    }

    /**
     * Adds a new Payment method to this PaymentDirectory.
     */
    public void addPaymentMtd() {
        PaymentXlsxHelper paymentXlsxHelper = new PaymentXlsxHelper();
        displayPaymentMethods(this.paymentDirectory);
        int numExistingRecords = this.getPaymentMethods().size();

        // Add a new method
        int paymentCategory = validateIntRange("Please select payment type to add from below:\n1. Credit/Debit Card\n2. Online\nSelect option: ", 1, 2);
        String newPaymentMethod = validateString("Please enter name of new payment method: ");

        Payment payment = null;
        switch (paymentCategory) {
            case 1:
                payment = new CreditDebitFactory().createPayment(newPaymentMethod);
                break;
            case 2:
                payment = new OnlineFactory().createPayment(newPaymentMethod);
                break;
        }
        this.paymentDirectory.add(payment);

        // Save data
        assert payment != null;
        paymentXlsxHelper.writeToXlsx(payment, numExistingRecords);

        // Display updated payment method data
        System.out.println("Updated list of payment methods are: ");
        displayPaymentMethods(this.paymentDirectory);
    }

    /**
     * Removes an existing Payment method from this PaymentDirectory.
     */
    public void removePaymentMtd() {
        PaymentXlsxHelper paymentXlsxHelper = new PaymentXlsxHelper();
        // Request for which to remove if payment methods exist
        if (!this.paymentDirectory.isEmpty()) {
            displayPaymentMethods(this.paymentDirectory);
            // Remove method via integer user input
            int index = validateIntRange("Please enter option of which payment method to remove: ", 1, this.paymentDirectory.size());
            Payment mtdToRmv = this.paymentDirectory.get(index - 1);
            this.paymentDirectory.remove(mtdToRmv);

            // Save new payment method data
            paymentXlsxHelper.removeXlsx(mtdToRmv);

            // Display updated payment method data
            System.out.println("Payment method removed successfully.\nUpdated list of payment methods:");
            displayPaymentMethods(this.paymentDirectory);
        }
    }
}