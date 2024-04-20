package payment;

import java.util.ArrayList;

public class PaymentView {
    public static void displayPaymentMethods(ArrayList<Payment> paymentList) {
        if (!paymentList.isEmpty()) {
            int counter = 1;
            System.out.println("Available payment methods: ");
            for (Payment item : paymentList) {
                System.out.println(counter + ". " + item.getPaymentMethod());
                counter++;
            }
            System.out.println();
        } else {
            System.out.println("No payment methods exist currently.");
        }
    }
}
