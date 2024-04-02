package payment;

import staff.StaffRoles;

import java.util.ArrayList;
import java.util.Objects;

import static authorisation.Authorisation.authoriseAdmin;
// TODO: VIEW TO DISPLAY PAYMENT METHODS
// Records of payment methods
public class PaymentDirectory {
    // Attributes
    private ArrayList<Payment> paymentDirectory;

    // Constructor
    public PaymentDirectory(ArrayList<Payment> paymentDirectory) {
        this.paymentDirectory = paymentDirectory;
    }

    // Functionalities
    // Get all payment methods
    public ArrayList<Payment> getPaymentDirectory() {
        return this.paymentDirectory;
    }

    // Get a specific payment method
    public Payment getPaymentMtd(String method) {
        // Return payment object if it can be found
        for (Payment payment : this.paymentDirectory) {
            if (Objects.equals(payment.getPaymentMethod(), method))
                return payment;
        }
        // Return null if payment cannot be found
        return null;
    }

    // Add payment method
    public void addPaymentMtd(Payment payment, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.paymentDirectory.add(payment);
    }

    // Remove payment method
    public void rmvPaymentMtd(String paymentMtd, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.paymentDirectory.remove(this.getPaymentMtd(paymentMtd));
    }
}
