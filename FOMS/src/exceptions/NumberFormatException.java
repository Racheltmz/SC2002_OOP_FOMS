package exceptions;

public class NumberFormatException extends Exception {
    public NumberFormatException() {
        super("Number Format Exception occurred.");
    }
    public NumberFormatException(String msg) {
        super(msg);
    }
}
