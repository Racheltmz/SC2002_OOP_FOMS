package validation;

import utils.InputScanner;

import java.util.InputMismatchException;

import static utils.InputScanner.getInstance;

public class ValidateDataType {
    // Check integer
    public static int validateInt(String msg) {
        InputScanner sc = getInstance();
        boolean success = false;
        int input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextInt();
                success = true;
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

    // Check double
    public static double validateDouble(String msg) {
        InputScanner sc = getInstance();
        boolean success = false;
        double input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextDouble();
                success = true;
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid float.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }
}
