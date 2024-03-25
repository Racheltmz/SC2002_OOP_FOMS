package Payment;

public class PayPal implements PaymentMethod {
    public void processPayment(double amount, String orderID){
        System.out.println("Payment processed using PayPal.");
    }
}
