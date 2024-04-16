package payment;

import java.util.ServiceLoader;

public class PaymentLoader {
    protected static int loadMethods() {
        int counter = 0;
        ServiceLoader<Payment> loader = ServiceLoader.load(Payment.class);
        for (Payment service : loader) {
            counter++;
            System.out.println(counter + "." + service.getPaymentMethod());
        }
        return counter;
    }

    public static void main(String[] args) {
        loadMethods();
    }
}