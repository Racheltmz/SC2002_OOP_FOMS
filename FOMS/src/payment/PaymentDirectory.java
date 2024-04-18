package payment;

import io.PaymentTxtHelper;

import java.util.ArrayList;

import static payment.PaymentView.displayPaymentMethods;
import static utils.ValidateHelper.validateIntRange;
import static utils.ValidateHelper.validateString;

// TODO: check payment implementation
// Records of payment methods
public class PaymentDirectory {
    // Attributes
    private final ArrayList<String> paymentDirectory;
    private static PaymentDirectory paymentSingleton = null;

    private PaymentDirectory() {
        PaymentTxtHelper paymentTxtHelper = new PaymentTxtHelper();
        this.paymentDirectory = paymentTxtHelper.readPaymentMethods();
    }

    public static PaymentDirectory getInstance() {
        if (paymentSingleton == null) {
            paymentSingleton = new PaymentDirectory();
        }
        return paymentSingleton;
    }

    public ArrayList<String> getPaymentMtds() {
        return this.paymentDirectory;
    }

    // Add payment method
    public void addPaymentMtd() {
        PaymentTxtHelper paymentTxtHelper = new PaymentTxtHelper();
        displayPaymentMethods(this.paymentDirectory);

        // Add a new method
        String newPaymentMethod = validateString("Please enter name of new payment method: ");
        newPaymentMethod = Character.toUpperCase(newPaymentMethod.charAt(0)) + newPaymentMethod.substring(1);
        this.paymentDirectory.add(newPaymentMethod);

        // Save data
        paymentTxtHelper.writePaymentMethod(this.paymentDirectory); // newPaymentMethod

        // Display updated payment method data
        System.out.println("Updated list of payment methods are: ");
        displayPaymentMethods(this.paymentDirectory);
    }

    public void removePaymentMtd() {
        PaymentTxtHelper paymentTxtHelper = new PaymentTxtHelper();
        // Request for which to remove if payment methods exist
        if (!this.paymentDirectory.isEmpty()) {
            // Remove method via integer user input
            int index = validateIntRange("Please enter option of which payment method to remove: ", 1, this.paymentDirectory.size(), true);
            String paymentMethodToRemove = this.paymentDirectory.get(index - 1);
            this.paymentDirectory.remove(paymentMethodToRemove);

            // Save new payment method data
            paymentTxtHelper.writePaymentMethod(this.paymentDirectory);

            // Display updated payment method data
            System.out.println("Payment method removed successfully.\nUpdated list of payment methods:");
            displayPaymentMethods(this.paymentDirectory);
        }
    }
}