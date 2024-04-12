package utils;

import payment.Payment;

import java.util.ArrayList;

// TODO: NEW: REMOVE AFTER IMPLEMENTING EXTENSIBILITY FOR PAYMENT
public class Initialisation {
    // Initialise payment records
    public static ArrayList<Payment> initialisePaymentRecords() {
        ArrayList<Payment> paymentList = new ArrayList<>();
        Payment card = new Payment("Credit/Debit Card");
        Payment online = new Payment("PayPal");
        paymentList.add(card);
        paymentList.add(online);
        return paymentList;
    }
}
