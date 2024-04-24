package management.actions;

import payment.PaymentDirectory;

/**
 * The AdminPaymentActions class provides methods for Admins to manage available payment methods,
 * such as adding a new payment method and removing an existing payment method.
 */
public class AdminPaymentActions implements IAdminPaymentActions {
    PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();

    /**
     * Adds a new payment method to the paymentDirectory.
     */
    public void addPayment() {
        paymentDirectory.addPaymentMtd();
    }

    /**
     * Removes an existing payment method from the paymentDirectory.
     */
    public void rmvPayment() {
        paymentDirectory.removePaymentMtd();
    }
}
