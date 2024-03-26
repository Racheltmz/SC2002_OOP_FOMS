package Payments;

public interface PaymentMethod {
    void processPayment(double amount, String orderID) throws PaymentException;
}
