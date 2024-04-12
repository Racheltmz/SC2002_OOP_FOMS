package payment;

import java.util.ServiceLoader;

public class PaymentLoader {
    public static void loadMethods() {
        ServiceLoader<PaymentService> loader = ServiceLoader.load(PaymentService.class);
        for (PaymentService service : loader) {
            System.out.println(service.getPaymentMethod());
        }
    }
}