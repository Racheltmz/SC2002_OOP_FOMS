package payment;

import utils.InputScanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static utils.InputScanner.getInstance;

public class PaymentManager {
    private static ServiceLoader<PaymentService> loader;
    private static final String CONFIG_FILE_PATH = "FOMS/src/payment/META-INF/services/payment.PaymentService";
    public static List<String> readPaymentMethods() {
        List<String> paymentMethods = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                paymentMethods.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentMethods;
    }

    public static void addPaymentMethod() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH, true))) {
            InputScanner sc = getInstance();
            System.out.println("Enter new payment method: ");
            String newPaymentMethod = sc.next();
            writer.write(newPaymentMethod);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removePaymentMethod() {
        List<String> paymentMethods = readPaymentMethods();
        System.out.println("Current payment methods are: ");
        System.out.print(paymentMethods);
        boolean found = false;

        while (!found) {
            InputScanner sc = getInstance();
            System.out.println("Enter payment method to remove: ");
            String paymentMethodToRemove = sc.next();

            for (String method : paymentMethods) {
                if (method.equalsIgnoreCase(paymentMethodToRemove)) {
                    found = true;
                    paymentMethods.remove(paymentMethodToRemove);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
                        for (String Method : paymentMethods) {
                            writer.write(Method);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Method not found. Please try again.");
        }
    }

    public static void displayPaymentMethods() {
        for (PaymentService service : loader) {
            System.out.println(service.getPaymentMethod());
        }
    }
}
