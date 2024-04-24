package management.actions;

/**
 * Interface defining Admin's administrative actions related to payments.
 */
public interface IAdminPaymentActions {

    /**
     * Adds a new payment method.
     */
    void addPayment();

    /**
     * Removes an existing payment method.
     */
    void rmvPayment();
}
