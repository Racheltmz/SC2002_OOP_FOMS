package io;

import payment.Payment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ServiceLoader;

import static io.FileIOHelper.getFile;

/**
 * IO Operations for Payment.
 */
public class PaymentFileHelper {
    /**
     * Path to Payment File in the META-INF/services folder.
     */
    private final String PAYMENTMANIFEST = "./src/META-INF/services/payment.Payment";

    /**
     * Default Constructor.
     */
    public PaymentFileHelper() {}

    /**
     * Singleton instance of payment txt helper.
     */
    private static PaymentFileHelper payInstance;

    /**
     * Gets the singleton instance of PaymentTxtHelper that reads from payment_methods.txt
     *
     * @return Instance of this class
     */
    public static PaymentFileHelper getInstance() {
        if (payInstance == null)
            payInstance = new PaymentFileHelper();
        return payInstance;
    }

    public void writePaymentMethod(String paymentMethod) {
        try {
            PrintWriter fileOut = new PrintWriter(new FileWriter(this.PAYMENTMANIFEST, true));
            fileOut.print("payment." + paymentMethod);
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Unable to add payment method.");
        }
    }

//    public void rmvPaymentMethod(String paymentMethod) {
//        try {
//            PrintWriter fileOut = new PrintWriter(getFile(this.PAYMENTMANIFEST));
//            fileOut.println("payment." + paymentMethod);
//            fileOut.close();
//        } catch (IOException e) {
//            System.out.println("Unable to add payment method.");
//        }
//    }
}
