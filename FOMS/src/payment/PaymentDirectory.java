package payment;

import io.PaymentFileHelper;
import utils.InputScanner;

import java.util.ArrayList;
import java.util.ServiceLoader;

/**
 * Manage payment methods with extensibility.
  */

public class PaymentDirectory {
    public static void displayPaymentMethods(ArrayList<Payment> paymentList) {
        int counter = 0;
        System.out.println("Payment Methods:");
        for (Payment payment : paymentList) {
            counter++;
            System.out.println(counter + ". " + payment.getPaymentMethod());
        }
    }

    /**
     * Display all payment methods.
     */

    public ArrayList<Payment> getPaymentMethods() {
        ArrayList<Payment> paymentList = new ArrayList<>();
        ServiceLoader<Payment> loader = ServiceLoader.load(Payment.class);
        loader.reload();
        for (Payment payment : loader) {
            paymentList.add(payment);
        }
        return paymentList;
    }

    // Add payment method
    public void addPaymentMtd() {
        InputScanner sc = InputScanner.getInstance();
        PaymentFileHelper paymentFileHelper = new PaymentFileHelper();
        displayPaymentMethods(getPaymentMethods());

        // Add a new method
        System.out.print("Please enter name of new payment method: ");
        String newMethod = sc.next();
        paymentFileHelper.writePaymentMethod(newMethod);

        // Display updated payment method data
        System.out.println("Updated list of payment methods are: ");
        displayPaymentMethods(getPaymentMethods());
    }

    public void removePaymentMtd() {
        PaymentFileHelper paymentTxtHelper = new PaymentFileHelper();
//        // Request for which to remove if payment methods exist
//        if (!this.paymentDirectory.isEmpty()) {
//            this.displayPaymentMethods();
//            // Remove method via integer user input
//            int index = validateIntRange("Please enter option of which payment method to remove: ", 1, this.paymentDirectory.size());
//            String paymentMethodToRemove = this.paymentDirectory.get(index - 1);
//            this.paymentDirectory.remove(paymentMethodToRemove);
//
//            // Save new payment method data
//            paymentTxtHelper.writePaymentMethod(this.paymentDirectory);
//
//            // Display updated payment method data
//            System.out.println("Payment method removed successfully.\nUpdated list of payment methods:");
//            this.displayPaymentMethods();
//        }
    }
}