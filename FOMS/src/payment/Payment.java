package payment;

// Payment Method Details
public class Payment {
    // Attributes
    private String paymentMethod;

    // Constructor
    public Payment(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    // Getter
    public String getPaymentMethod() {
        return paymentMethod;
    }

}