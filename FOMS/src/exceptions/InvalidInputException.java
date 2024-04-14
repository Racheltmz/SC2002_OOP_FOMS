package exceptions;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("Input is invalid. Please enter value again");
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }
}
