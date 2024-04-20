package payment;

import io.IXlsxSerializable;
import utils.InputScanner;

/**
 * PayPal Payment Method.
 */
public class Online extends Payment implements IXlsxSerializable {
    private final String category = "Online";
    private final String paymentMethod;

    public Online(String method) {
        this.paymentMethod = method;
    }

    /**
     * Convert object to string for serialisation.
     * @return String array of Online payment object.
     */
    public String[] toXlsx() {return new String[] { category, paymentMethod }; }

    /**
     * Get payment category
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Payment method name.
     */
    @Override
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Get details required PayPal.
     */
    public void getDetails() {
        InputScanner sc = InputScanner.getInstance();
        String phoneNum;
        while (true) {
            System.out.print("Enter mobile number: ");
            phoneNum = sc.next();
            if (phoneNum.length() == 8) {
                break;
            } else {
                System.out.println("Invalid number, please enter 8 digits.");
            }
        };
    }
}
