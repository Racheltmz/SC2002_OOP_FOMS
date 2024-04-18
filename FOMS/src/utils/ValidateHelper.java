package utils;

import exceptions.InputStringMismatch;
import exceptions.InvalidInputException;
import exceptions.InvalidStaffQuotaException;

import java.util.InputMismatchException;

import static utils.InputScanner.getInstance;

public class ValidateHelper {
    // Check integer
    public static int validateInt(String msg) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        int input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextInt();
                if (input >= 0)
                    success = true;
                else
                    System.out.println("Negative values are invalid.");
            }  catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

    public static int validateIntRange(String msg, int start, int end) {
        InputScanner sc = getInstance();
        int input = validateInt(msg);
        while (input > end || input < start) {
            System.out.println("Please enter a valid integer.\n");
            input = validateInt(msg);
        }
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
                if (input >= 0)
                    success = true;
                else
                    System.out.println("Negative values are invalid.");
            }  catch (InputMismatchException e) {
                System.out.println("Please enter a valid float.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

    public static String validateString(String msg) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        String input = null;
        do {
            try {
                System.out.println(msg);
                input = sc.next();
                input += sc.nextLine();
                if (input == null) {
                    System.out.println("Please enter a valid input.\n");
                }
                else if (input != null) {
                    success = true;
                }
                else if (!input.matches("[a-zA-Z]+")) {
                    throw new InputStringMismatch();
                }
            }  catch (InputStringMismatch e) {
                System.out.println("Please enter a valid input.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

}
