package management.actions;

import payment.PaymentDirectory;

public class AdminPaymentActions implements IAdminPaymentActions {


    PaymentDirectory paymentDirectory = new PaymentDirectory();
    public void addPayment() {
        paymentDirectory.addPaymentMtd();
    }

    public void rmvPayment() {
        paymentDirectory.removePaymentMtd();
    }
}
