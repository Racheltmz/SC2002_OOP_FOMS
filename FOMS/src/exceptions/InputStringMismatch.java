package exceptions;

/**
 * The InputStringMismatch class represents an exception that occurs when an inputted String is empty or not alphanumerical.
 * This exception is thrown when a user enters a String that is empty or not alphanumerical.
 */
public class InputStringMismatch extends Exception {
    /**
     * Constructs a new InputStringMismatch with the specified detail message.
     *
     * @param message The detail message to be printed when an InputStringMismatch exception occurs.
     */
    public InputStringMismatch(String message) { super(message); }
}
