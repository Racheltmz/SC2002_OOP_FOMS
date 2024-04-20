package payment;

public class OnlineFactory implements PaymentFactory {
    @Override
    public Payment createPayment(String method) {
        return new Online(method);
    }
}
