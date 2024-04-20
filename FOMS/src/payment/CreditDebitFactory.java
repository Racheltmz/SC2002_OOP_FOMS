package payment;

public class CreditDebitFactory implements PaymentFactory {
    @Override
    public Payment createPayment(String method) {
        return new CreditDebitCard(method);
    }
}
