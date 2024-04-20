package payment;

import utils.InputScanner;

/**
 * Credit/Debit Card Payment Method.
 */
public class CreditDebitCard extends Payment {
    private final String category = "Credit/Debit Card";
    private final String paymentMethod;

    public CreditDebitCard(String method) {
        this.paymentMethod = method;
    }

    /**
     * Convert object to string for serialisation.
     * @return String array of Credit/Debit Card payment object.
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
     * Get payment method name.
     */
    @Override
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Get details required Credit/Debit card.
     */
    @Override
    public void getDetails() {
        InputScanner sc = InputScanner.getInstance();
        String cardNum;
        String cardCVV;
        while (true) {
            System.out.print("Enter the card number: ");
            cardNum = sc.next();
            if (cardNum.length() == 16) {
                break;
            } else {
                System.out.println("Invalid card number, need 16 digits.");
            }
        }
        while (true) {
            System.out.print("Enter the CVV: ");
            cardCVV = sc.next();
            if (cardCVV.length() == 3) {
                break;
            } else {
                System.out.println("Invalid CVV, need 3 digits.");
            }
        }
    }
}
