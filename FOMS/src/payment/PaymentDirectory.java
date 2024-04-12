package payment;

import exceptions.ItemNotFoundException;
import staff.StaffRoles;
import exceptions.EmptyListException;

import java.util.ArrayList;
import java.util.Objects;

import static authorisation.Authorisation.authoriseAdmin;
import static utils.Initialisation.initialisePaymentRecords;

// Records of payment methods
public class PaymentDirectory {
    // Attributes
    private final ArrayList<Payment> paymentDirectory;
    private static PaymentDirectory paymentSingleton = null;
    private PaymentDirectory() {
        this.paymentDirectory = initialisePaymentRecords();
    }

    public static PaymentDirectory getInstance() {
        if (paymentSingleton == null) {
            paymentSingleton = new PaymentDirectory();
        }
        return paymentSingleton;
    }

    // Functionalities
    // Get all payment methods
    public ArrayList<Payment> getPaymentDirectory() {
        return this.paymentDirectory;
    }

    /**
     * Check if there are any existing payment methods
     *
     * @return boolean
     */
    public boolean methodsExist() {
        try {
            if (this.paymentDirectory.isEmpty())
                throw new EmptyListException("No payment methods.");
            else
                return true;
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Get a specific payment method
    // TODO: NEW: Review if we shld do something similar to OrderQueue getOrderById
    public Payment getPaymentMtd(String method) {
        if (methodsExist()) {
            try {
                // Return payment object if it can be found
                for (Payment payment : this.paymentDirectory) {
                    if (Objects.equals(payment.getPaymentMethod(), method))
                        return payment;
                }
                throw new ItemNotFoundException("Payment method cannot be found ");
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // Add payment method
    public void addPaymentMtd(Payment payment, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            if (methodsExist())
                this.paymentDirectory.add(payment);
        }
    }

    // Remove payment method
    public void rmvPaymentMtd(String paymentMtd, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            if (methodsExist())
                this.paymentDirectory.remove(getPaymentMtd(paymentMtd));
        }
    }
}
