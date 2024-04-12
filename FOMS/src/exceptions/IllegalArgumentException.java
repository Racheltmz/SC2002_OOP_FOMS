package exceptions;

public class IllegalArgumentException extends Exception {
    public IllegalArgumentException() {
        super("Illegal Argument Exception occurred.");
    }
    public IllegalArgumentException(String msg) {
        super(msg);
    }

}