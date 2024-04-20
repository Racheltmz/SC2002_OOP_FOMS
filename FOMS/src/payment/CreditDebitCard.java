package payment;

import utils.InputScanner;

/**
 * Credit/Debit Card Payment Method.
 */
public class CreditDebitCard extends Payment {
    /**
     * Payment method name.
     */
    @Override
    public String getPaymentMethod() {
        return "Credit/Debit Card";
    }

    /**
     * Get details required Credit/Debit card.
     */
    @Override
    public void getDetails() {
        InputScanner sc = InputScanner.getInstance();
        String cardNum;
        String cardCVV;
        do {
            System.out.print("Enter the card number: ");
            cardNum = sc.next();

            System.out.print("Enter the CVV: ");
            cardCVV = sc.next();
        } while (!(cardNum.length() == 16 && cardCVV.length() == 3));
    }
}
