package payment;

/**
 * Abstract class for Payment Methods.
 */
public abstract class Payment {
    /**
     * Get payment category.
     * @return String of the payment category.
     */
    public abstract String getCategory();

    /**
     * Get payment method.
     * @return String of the method name.
     */
    public abstract String getPaymentMethod();

    /**
     * Get details required by the payment method.
     */
    public abstract void getDetails();

    /**
     * Convert payment object to string for serialisation.
     * @return String array of payment object.
     */
    public abstract String[] toXlsx();
}