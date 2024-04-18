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

    public static int validateIntRange(String msg, int start, int end, boolean ignoreBlank) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        int input = 0;
        do {
            try {
//                if (ignoreBlank) {
//                    System.out.println(msg);
//                    if (sc.hasNextInt()) {
//
//                    }
//                } else {
                input = validateInt(msg);
//                }
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

    public static String validateString(String msg) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        String input = null;
        do {
            try {
                System.out.println(msg);
                input = sc.next();
                input += sc.nextLine();
                if (input.isBlank()) {
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
    private static String validateCategories(String msg, String[] categories, String errMsg) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        String input = null;
        do {
            try {
                System.out.print(msg);
                input = sc.next().toUpperCase();
                for (String category : categories) {
                    if (input.equalsIgnoreCase(category)) {
                        success = true;
                        break;
                    }
                }
                throw new InvalidInputException(errMsg);
            }  catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (!success);
        return input;
    }

    public static String validateGender() {
        String msg = "Enter gender (M/F): ";
        String[] genderCategory = {"M", "F"};
        String errMsg = "Invalid input. Please enter M or F";
        return validateCategories(msg, genderCategory, errMsg);
    }

    public static String validateRole() {
        String msg = "Enter role (S/M):\nS: Staff\nM: Manager\n";
        String[] roleCategory = {"S", "M"};
        String errMsg = "Invalid input. Please enter S or M";
        return validateCategories(msg, roleCategory, errMsg);
    }
}
