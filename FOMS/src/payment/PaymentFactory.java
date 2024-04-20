package payment;

public interface PaymentFactory {
    Payment createPayment(String method);
}
