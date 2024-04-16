package payment;

// Payment Method Details
public abstract class Payment {
    // Attributes
    private String paymentMethod;

    // Getter
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }

}
