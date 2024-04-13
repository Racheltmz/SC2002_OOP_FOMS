package payment;

import staff.StaffRoles;
import utils.InputScanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static authorisation.Authorisation.authoriseAdmin;
import static staff.StaffRoles.ADMIN;
import static utils.InputScanner.getInstance;

public class PaymentManager {
    private static ServiceLoader<PaymentService> loader;
    private static final String CONFIG_FILE_PATH = "FOMS/src/payment/META-INF/services/payment.PaymentService";
    private static List<String> readPaymentMethods() {
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

    public static void addPaymentMethod(StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH, true))) {
                InputScanner sc = getInstance();
                System.out.println("Enter new payment method: ");
                String newPaymentMethod = sc.next();
                newPaymentMethod += sc.nextLine();
                writer.write(newPaymentMethod);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("You are not authorised to perform this action.");
        }
    }

    public static void removePaymentMethod(StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            List<String> paymentMethods = readPaymentMethods();
            System.out.println("Current payment methods are: ");
            displayPaymentMethods();
            boolean found = false;

            while (!found) {
                InputScanner sc = getInstance();
                System.out.println("Enter payment method to remove: ");
                String paymentMethodToRemove = sc.next();
                paymentMethodToRemove += sc.nextLine();

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
        else
        {
            System.out.println("You are not authorised to perform this action.");
        }
    }

    public static int displayPaymentMethods() {
        return PaymentLoader.loadMethods();
    }
}
