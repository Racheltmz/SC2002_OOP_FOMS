package payment;

/**
 * Abstract class for Payment Methods.
 */
public abstract class Payment {
    /**
     * Get payment method.
     * @return String of the method name.
     */
    public abstract String getPaymentMethod();

    /**
     * Get details required by the payment method.
     */
    public abstract void getDetails();
}