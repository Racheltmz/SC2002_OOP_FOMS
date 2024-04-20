package payment;

import java.util.ArrayList;

public class PaymentView {
    public static void displayPaymentMethods(ArrayList<String> paymentList) {
        if (!paymentList.isEmpty()) {
            int counter = 1;
            System.out.println("Available payment methods: ");
            for (String item : paymentList) {
                System.out.println(counter + ". " + item + " payment");
                counter++;
            }
        } else {
            System.out.println("No payment methods exist currently.");
        }
    }
}
