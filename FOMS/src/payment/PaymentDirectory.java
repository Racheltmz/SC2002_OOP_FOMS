package payment;

import exceptions.ItemNotFoundException;
import staff.StaffRoles;
import exceptions.EmptyListException;
import utils.InputScanner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static authorisation.Authorisation.authoriseAdmin;
import static utils.Initialisation.initialisePaymentRecords;
import static utils.ValidateHelper.validateIntRange;

// Records of payment methods
public class PaymentDirectory {
    // Attributes
    private final ArrayList<Payment> paymentDirectory;
    private static PaymentDirectory paymentSingleton = null;
    private PaymentDirectory() {
        this.paymentDirectory = initialisePaymentRecords();
    }

    public static PaymentDirectory getInstance() {
        if (paymentSingleton == null) {
            paymentSingleton = new PaymentDirectory();
        }
        return paymentSingleton;
    }

    // Functionalities
    // Get all payment methods
    public ArrayList<Payment> getPaymentDirectory() {
        return this.paymentDirectory;
    }

    /**
     * Check if there are any existing payment methods
     *
     * @return boolean
     */
    public boolean methodsExist() {
        try {
            if (this.paymentDirectory.isEmpty())
                throw new EmptyListException("No payment methods.");
            else
                return true;
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Get a specific payment method
    // TODO: NEW: Review if we shld do something similar to OrderQueue getOrderById
    public Payment getPaymentMtd(String method) {
        if (methodsExist()) {
            try {
                // Return payment object if it can be found
                for (Payment payment : this.paymentDirectory) {
                    if (Objects.equals(payment.getPaymentMethod(), method))
                        return payment;
                }
                throw new ItemNotFoundException("Payment method cannot be found ");
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // Add payment method
    // TODO: RACHEL HELP TO TEST NEW PERSISTENCE METHOD
    public static void addPaymentMtd() {
        InputScanner sc = InputScanner.getInstance();

        // Read payment method data
        ArrayList<String> paymentMethodList = readPaymentMethods();
        printPaymentMethods();

        // Add a new method
        System.out.print("Please enter name of new payment method: ");
        String newPaymentMethod = sc.next();
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
        printPaymentMethods();

        // Remove method via integer user input
        int index = validateIntRange("Please enter option of which payment method to remove: ", 1, paymentMethodList.size());
        String paymentMethodToRemove = paymentMethodList.get(index - 1);
        paymentMethodList.remove(paymentMethodToRemove);

        // Save new payment method data
        writePaymentMethod(paymentMethodList);

        // Display updated payment method data
        System.out.println("Payment method removed successfully.\nUpdated list of payment methods:");
        printPaymentMethods();
    }

    // Remove payment method
    public void rmvPaymentMtd(String paymentMtd, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            if (methodsExist())
                this.paymentDirectory.remove(getPaymentMtd(paymentMtd));
        }
    }

    public void displayPaymentMethods()
    {
        int counter = 1;
        for (Payment payment : paymentDirectory)
        {
            System.out.println(counter + ". " + payment.getPaymentMethod());
            counter++;
        }
    }

    private static ArrayList<String> readPaymentMethods(){
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

    private static void printPaymentMethods() {
        ArrayList<String> paymentList = readPaymentMethods();
        if (!paymentList.isEmpty()) {
            int counter = 1;
            System.out.println("Available payment methods: ");
            for (String item : paymentList) {
                System.out.println(counter + ". " + item + "payment");
                counter++;
            }
            System.out.println();
        }
    }
}
