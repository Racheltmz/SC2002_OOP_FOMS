package payment;

/**
 * Interface for factory of payment methods.
 */
public interface PaymentFactory {
    /**
     * Create payment method based on method name.
     * @param method Name of new payment method.
     * @return New Payment object.
     */
    Payment createPayment(String method);
}
