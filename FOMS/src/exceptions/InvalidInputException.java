package exceptions;

/**
 * The InvalidInputException class represents an exception that occurs when the inputted String format does not match with the required one.
 * This exception is thrown when an Admin attempts to filter by role/gender but does not enter the correct respective characters (F/M) or (S/M/A).
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message The detail message to be printed when an InvalidInputException exception occurs.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
