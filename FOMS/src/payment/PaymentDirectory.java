package payment;

import staff.StaffRoles;
import exceptions.AuthorizationException;
import exceptions.EmptyListException;

import java.util.ArrayList;
import java.util.Objects;

import static authorisation.Authorisation.authoriseAdmin;

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
    public Payment getPaymentMtd(String method) throws EmptyListException {
        // Return payment object if it can be found
        for (Payment payment : this.paymentDirectory) {
            if (Objects.equals(payment.getPaymentMethod(), method))
                return payment;
        } 
         throw new EmptyListException(" The payment cannot be found ");
        // Return null if payment cannot be found
    }

    // Add payment method
    public void addPaymentMtd(Payment payment, StaffRoles auth) throws AuthorizationException {
        if (authoriseAdmin(auth))
            this.paymentDirectory.add(payment);
        else
            throw new AuthorizationException("Unauthorized access: Admin authorization required to add payment method.");
    }

    // Remove payment method
    public void rmvPaymentMtd(String paymentMtd, StaffRoles auth) throws AuthorizationException, EmptyListException {
        if (authoriseAdmin(auth))
            this.paymentDirectory.remove(this.getPaymentMtd(paymentMtd));
        else
            throw new AuthorizationException("Unauthorized access: Admin authorization required to remove payment method.");
    }
}
