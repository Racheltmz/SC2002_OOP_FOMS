package utils;

import exceptions.InvalidStaffQuotaException;

import java.util.InputMismatchException;

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
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        int input = 0;
        do {
            try {
                input = validateInt(msg);
                if (start <= input && input <= end) {
                    success = true;
                } else {
                    throw new InvalidStaffQuotaException("Invalid range! Please re-enter.");
                }
            }  catch (InvalidStaffQuotaException e) {
                System.out.println(e.getMessage());
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
}
