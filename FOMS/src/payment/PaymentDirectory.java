package payment;

import staff.StaffRoles;

import java.util.ArrayList;
import java.util.Objects;

import static authorisation.Authorisation.authoriseAdmin;

// TODO: VIEW TO DISPLAY PAYMENT METHODS 
// Records of payment methods (DONE)

public class PaymentDirectory {
    // Attributes
    private static ArrayList<Payment> paymentDirectory;

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

    public static void displayPaymentMethods()
    {
        int counter = 1;
        for (Payment payment : paymentDirectory)
        {
            System.out.println(counter + ". " + payment.getPaymentMethod());
            counter++;
        }
    }
}
