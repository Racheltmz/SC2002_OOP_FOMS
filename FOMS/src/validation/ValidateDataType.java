package validation;

import utils.InputScanner;

import java.util.InputMismatchException;

public class ValidateDataType {
    // Check integer
    public static int validateInt(String msg) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        int input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextInt();
                // TODO: Improve implementation of handling negative vals
                if (input >= 0)
                    success = true;
                else
                    System.out.println("Negative values are invalid.");
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

    // Check double
    public static double validateDouble(String msg) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        double input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextDouble();
                // TODO: Improve implementation of handling negative vals
                if (input >= 0)
                    success = true;
                else
                    System.out.println("Negative values are invalid.");
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid float.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }
}
