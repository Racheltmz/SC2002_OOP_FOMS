package io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static io.FileIOHelper.getFile;

// TODO: review io in this file

public class PaymentTxtHelper {
    /**
     * Path to Order XLSX File in the data folder. Defaults to order_list.xlsx.
     */
    private final String paymentTxt = "payment_methods.txt";

    /**
     * Default Constructor.
     */
    public PaymentTxtHelper() {}

    /**
     * Singleton instance of payment txt helper.
     */
    private static PaymentTxtHelper payInstance;

    /**
     * Gets the singleton instance of PaymentTxtHelper that reads from payment_methods.txt
     *
     * @return Instance of this class
     */
    public static PaymentTxtHelper getInstance() {
        if (payInstance == null)
            payInstance = new PaymentTxtHelper();
        return payInstance;
    }

    public ArrayList<String> readPaymentMethods(){
        ArrayList<String> list = new ArrayList<>();
        try {
            // Create the Scanner with the data file
            Scanner fileIn = new Scanner(new File(this.paymentTxt));
            while (fileIn.hasNext()) {
                // Add to the ArrayList
                list.add(fileIn.nextLine());
            }
            fileIn.close();
        } catch (IOException e) {
            System.out.println("No payment methods exist currently.");
        }
        return list;
    }

    public void writePaymentMethod(ArrayList<String> paymentMethodList) {
        try {
            PrintWriter fileOut = new PrintWriter(getFile(this.paymentTxt));
            for (String method : paymentMethodList) {
                fileOut.println(method);
            }
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Unable to add payment method.");
        }
    }
}
