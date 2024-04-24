package exceptions;

/**
 * The ItemNotFoundException class represents an exception that occurs when the item to be found does not exist.
 * This exception is thrown when a user attempts to access a record that does not exist.
 */
public class ItemNotFoundException extends Exception {
    /**
     * Constructs a new ItemNotFoundException with the specified detail message.
     *
     * @param message The detail message to be printed when an ItemNotFoundException exception occurs.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}
