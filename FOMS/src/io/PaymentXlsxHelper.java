package io;

import order.Order;
import payment.CreditDebitFactory;
import payment.OnlineFactory;
import payment.Payment;

import java.util.*;

public class PaymentXlsxHelper extends BaseXlsxHelper {
    /**
     * Path to Order XLSX File in the data folder. Defaults to order_list.xlsx.
     */
    private final String paymentXlsx = "payment_list.xlsx";

    /**
     * Headers for menu item.
     */
    private final String[] header = {"category", "method"};

    /**
     * Default Constructor.
     */
    public PaymentXlsxHelper() {}

    /**
     * Singleton instance of payment txt helper.
     */
    private static PaymentXlsxHelper payInstance;

    /**
     * Gets the singleton instance of PaymentTxtHelper that reads from payment_methods.txt
     *
     * @return Instance of this class
     */
    public static PaymentXlsxHelper getInstance() {
        if (payInstance == null)
            payInstance = new PaymentXlsxHelper();
        return payInstance;
    }

    /**
     * On initialisation, reads the XLSX file and parses it into an array list of payment methods.
     *
     * @return ArrayList of Payment Objects.
     */
    public ArrayList<Payment> readFromXlsx() {
        // Initialise a list
        ArrayList<Payment> paymentList = new ArrayList<>();

        // Deserialize records
        List<String[]> XlsxData = deserializeRecords(this.paymentXlsx,2, 1);
        if (XlsxData.isEmpty()) {
            serializeHeader(this.paymentXlsx, this.header);
            return paymentList;
        }

        // Create items
        XlsxData.forEach((data) -> {
            String category = data[0];
            String method = data[1];

            Payment payment = null;
            switch (category) {
                case "Credit/Debit Card":
                    payment = new CreditDebitFactory().createPayment(method);
                    break;
                case "Online":
                    payment = new OnlineFactory().createPayment(method);
                    break;
            }
            paymentList.add(payment);
        });
        return paymentList;
    }

    /**
     * Writes an order record to the XLSX File.
     *
     * @param payment Payment record to add
     * @param numExistingRecords Number of existing order records.
     */
    public void writeToXlsx(Payment payment, int numExistingRecords) {
        serializeRecord(this.paymentXlsx, payment.toXlsx(), numExistingRecords);
    }

    /**
     * Deletes a Payment record in the XLSX File.
     *
     * @param payment Payment record to delete
     */
    public void removeXlsx(Payment payment) {
        deleteRecord(this.paymentXlsx, payment);
    }
}