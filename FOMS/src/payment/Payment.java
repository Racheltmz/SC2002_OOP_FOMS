package payment;

// Payment Method Details
public class Payment implements PaymentService {
    // Attributes
    private String paymentMethod;

    // Constructor
    public Payment(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    // Getter
    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }

}
