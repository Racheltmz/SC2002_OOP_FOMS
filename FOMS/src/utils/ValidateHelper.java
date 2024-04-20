package utils;

import exceptions.InputStringMismatch;
import exceptions.InvalidInputException;
import exceptions.InvalidStaffQuotaException;

import java.util.InputMismatchException;

/**
 * Helper for validation of user input. The functions will keep asking for the user's input until it is valid.
 */
public class ValidateHelper {
    /**
     * Check if integer inputted is valid.
     *
     * @param msg Message to be printed out for user input.
     * @return Integer from user input if valid.
     */
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

    // TODO: handle ignoreBlank
    /**
     * Check if integer inputted is valid and within a valid range.
     *
     * @param msg Message to be printed out for user input.
     * @return Integer from user input if valid.
     */
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
                    throw new InvalidStaffQuotaException("Invalid range! Please re-enter.\n");
                }
            }  catch (InvalidStaffQuotaException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

    /**
     * Check if double inputted is valid.
     *
     * @param msg Message to be printed out for user input.
     * @return Double from user input if valid.
     */
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

    /**
     * Validate the string to ensure it does not consist of invalid characters such as symbols.
     *
     * @return String if it is valid.
     */
    public static String validateString(String msg) {
        InputScanner sc = InputScanner.getInstance();
        boolean success = false;
        String input = null;
        do {
            try {
                System.out.println(msg);
                input = sc.next();
                input += sc.nextLine();
                if (input.isBlank() || input.matches("^[0-9]+$")) {
                    throw new InputStringMismatch("Please enter a valid input.\n");
                } else {
                    success = true;
                }
            } catch (InputStringMismatch e) {
                System.out.println(e.getMessage());
            }
        } while (!success);
        return input;
    }

    /**
     * Validate if the user inputted a valid category.
     *
     * @return String of the category if valid.
     */
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
                if (!success)
                    throw new InvalidInputException(errMsg);
            }  catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (!success);
        return input;
    }

    /**
     * Validate if the user inputted a valid gender.
     *
     * @return String of either M or F.
     */
    public static String validateGender() {
        String msg = "Enter gender (M/F): ";
        String[] genderCategory = {"M", "F"};
        String errMsg = "Invalid input. Please enter M or F";
        return validateCategories(msg, genderCategory, errMsg);
    }

    /**
     * Validate if the user inputted a valid role.
     *
     * @return String of either S or M.
     */
    public static String validateRole() {
        String msg = "Enter role (S/M):\nS: Staff\nM: Manager\n";
        String[] roleCategory = {"S", "M"};
        String errMsg = "Invalid input. Please enter S or M";
        return validateCategories(msg, roleCategory, errMsg);
    }
}
