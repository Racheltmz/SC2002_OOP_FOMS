package payment;

/**
 * Factory to create credit/debit card payment methods.
 */
public class CreditDebitFactory implements PaymentFactory {
    /**
     * Create credit/debit card payment method.
     * @param method Name of new credit/debit card payment method.
     * @return Payment object.
     */
    @Override
    public Payment createPayment(String method) {
        return new CreditDebitCard(method);
    }
}
