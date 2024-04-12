package payment;

import java.util.ArrayList;

public class PaymentView {
    public static void displayPaymentMethods(ArrayList<Payment> paymentMethods){
        System.out.println("Available Payment Methods:");
        for (Payment payment : paymentMethods){
            System.out.println(payment.getPaymentMethod());
        }
    }
}
