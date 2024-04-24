package payment;

import java.util.ArrayList;

/**
 * View for Payment purposes.
 */
public class PaymentView {
    /**
     * Display all payment methods.
     * @param paymentList Array list of all payment methods.
     */
    public static void displayPaymentMethods(ArrayList<Payment> paymentList) {
        if (!paymentList.isEmpty()) {
            int counter = 1;
            System.out.println("Available payment methods: ");
            for (Payment item : paymentList) {
                System.out.println(counter + ". " + item.getPaymentMethod());
                counter++;
            }
        } else {
            System.out.println("No payment methods exist currently.");
        }
    }
}
