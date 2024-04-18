package payment;

import utils.InputScanner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static utils.ValidateHelper.validateIntRange;
import static utils.ValidateHelper.validateString;

// Records of payment methods
public class PaymentDirectory {
    // Attributes
    private static PaymentDirectory paymentSingleton = null;

    public static PaymentDirectory getInstance() {
        if (paymentSingleton == null) {
            paymentSingleton = new PaymentDirectory();
        }
        return paymentSingleton;
    }

    // Functionalities
    // Get all payment methods
//    public ArrayList<Payment> getPaymentDirectory() {
//        return this.paymentDirectory;
//    }

    /**
     * Check if there are any existing payment methods
     *
     * @return boolean
     */
//    public boolean methodsExist() {
//        try {
//            if (this.paymentDirectory.isEmpty())
//                throw new EmptyListException("No payment methods.");
//            else
//                return true;
//        } catch (EmptyListException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }

    // Get a specific payment method
    // TODO: NEW: Review if we shld do something similar to OrderQueue getOrderById
//    public Payment getPaymentMtd(String method) {
//        if (methodsExist()) {
//            try {
//                // Return payment object if it can be found
//                for (Payment payment : this.paymentDirectory) {
//                    if (Objects.equals(payment.getPaymentMethod(), method))
//                        return payment;
//                }
//                throw new ItemNotFoundException("Payment method cannot be found ");
//            } catch (ItemNotFoundException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return null;
//    }

    // Add payment method
    public static void addPaymentMtd() {
        InputScanner sc = InputScanner.getInstance();

        // Read payment method data
        ArrayList<String> paymentMethodList = readPaymentMethods();
        printPaymentMethods();

        // Add a new method
        String newPaymentMethod = validateString("Please enter name of new payment method: ");
        newPaymentMethod = Character.toUpperCase(newPaymentMethod.charAt(0)) + newPaymentMethod.substring(1);
        paymentMethodList.add(newPaymentMethod);

        // Save data
        writePaymentMethod(paymentMethodList);

        // Display updated payment method data
        System.out.println("Updated list of payment methods are: ");
        printPaymentMethods();
    }

    public static void removePaymentMtd() {
        InputScanner sc = InputScanner.getInstance();

        // Read payment method data
        ArrayList<String> paymentMethodList = readPaymentMethods();

        // Request for which to remove if payment methods exist
        if (printPaymentMethods()) {
            // Remove method via integer user input
            int index = validateIntRange("Please enter option of which payment method to remove: ", 1, paymentMethodList.size(), true);
            String paymentMethodToRemove = paymentMethodList.get(index - 1);
            paymentMethodList.remove(paymentMethodToRemove);

            // Save new payment method data
            writePaymentMethod(paymentMethodList);

            // Display updated payment method data
            System.out.println("Payment method removed successfully.\nUpdated list of payment methods:");
            printPaymentMethods();
        }
    }

//    public void displayPaymentMethods()
//    {
//        int counter = 1;
//        for (Payment payment : paymentDirectory)
//        {
//            System.out.println(counter + ". " + payment.getPaymentMethod());
//            counter++;
//        }
//    }

    public static ArrayList<String> readPaymentMethods(){
        ArrayList<String> list = new ArrayList<>();
        try {
            // Create the Scanner with the data file
            Scanner fileIn = new Scanner(new File("payment_methods.txt"));
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

    private static void writePaymentMethod(ArrayList<String> paymentMethodList) {
        try {
            PrintWriter fileOut = new PrintWriter("payment_methods.txt");
            for (String method : paymentMethodList) {
                fileOut.println(method);
            }
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Unable to add payment method.");
        }
    }

    public static boolean printPaymentMethods() {
        ArrayList<String> paymentList = readPaymentMethods();
        if (!paymentList.isEmpty()) {
            int counter = 1;
            System.out.println("Available payment methods: ");
            for (String item : paymentList) {
                System.out.println(counter + ". " + item + " payment");
                counter++;
            }
            System.out.println();
            return true;
        } else {
            System.out.println("No payment methods exist currently.");
            return false;
        }
    }
}