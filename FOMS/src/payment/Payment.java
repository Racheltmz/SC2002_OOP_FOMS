package payment;

// Payment Method Details
public class Payment {
    // Attributes
    private String paymentMethod;

    // Constructor
    public Payment(String method){
        this.paymentMethod = method;
    }

    // Getter
    public String getPaymentMethod() {
        return paymentMethod;
    }

}
