package payment;

import utils.InputScanner;

/**
 * PayPal Payment Method.
 */
public class PayPal extends Payment {
    /**
     * Payment method name.
     */
    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }
    /**
     * Get details required PayPal.
     */
    public void getDetails() {
        InputScanner sc = InputScanner.getInstance();
        String email;
        do {
            System.out.print("Enter email address: ");
            email = sc.next();
        } while (email != null);
    }
}
