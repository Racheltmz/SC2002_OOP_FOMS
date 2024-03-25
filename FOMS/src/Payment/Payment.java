package Payment;

// Payment Methods for FOMS
public class Payment {
    private PaymentMethod paymentMethod;

    public Payment(String method){
        this.paymentMethod = addOption(method);
    }

    // Get payment option (call company's method)
    private PaymentMethod addOption(String method){
        switch(method){
            case "Credit/Debit Card":
                return new CreditDebit();
            case "PayPal":
                return new PayPal();
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }

    // Remove payment option (call company's method)
    protected void removeOption(String method){
        if (paymentMethod != null && paymentMethod.getClass().getSimpleName().equals(method)){
            paymentMethod = null;
        }
    }

    // Payment message from processing
    public void processPayment(double amount, String orderID) throws PaymentException{
        if (paymentMethod != null){
            paymentMethod.processPayment(amount,orderID);
        }
        else{
            throw new PaymentException("No payment method selected.");
        }
    }
}
