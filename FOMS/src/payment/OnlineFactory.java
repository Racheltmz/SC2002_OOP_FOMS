package payment;

/**
 * Factory to create online payment methods.
 */
public class OnlineFactory implements PaymentFactory {
    /**
     * Create online payment method.
     * @param method Name of new online payment method.
     * @return Payment object.
     */
    @Override
    public Payment createPayment(String method) {
        return new Online(method);
    }
}
