package Validation;

import Initialisation.InputScanner;

import java.util.InputMismatchException;

import static Initialisation.InputScanner.getInstance;

public class Validate {
    public String validateType(String msg) {
        InputScanner sc = getInstance();
        boolean success = false;
        String input = null;
        do {
            try {
                System.out.println(msg);
                input = sc.next();
                success = true;
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer.\n");
                sc.nextLine();
            }
        } while (success);
        return input;
    }


}
