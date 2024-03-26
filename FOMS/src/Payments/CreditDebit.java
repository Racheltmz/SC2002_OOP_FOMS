package Payments;

public class CreditDebit implements PaymentMethod {
    public void processPayment(double amount, String orderID) throws PaymentException{
        System.out.println("Payment processed using Credit/Debit Card.");
    }
}