package payment;

import utils.InputScanner;

/**
 * ApplePay Payment Method.
 */
public class ApplePay extends Payment {
    /**
     * Payment method name.
     */
    @Override
    public String getPaymentMethod() {
        return "ApplePay";
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
