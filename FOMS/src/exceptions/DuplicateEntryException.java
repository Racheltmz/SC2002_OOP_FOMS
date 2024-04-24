package exceptions;

/**
 * The DuplicateEntryException class represents an exception that occurs when there is an attempt to add a duplicate MenuItem or Staff member.
 * This exception is thrown when a Manager attempts to add a duplicate MenuItem, or when an Admin attempts to add a duplicate Staff member.
 */
public class DuplicateEntryException extends Exception {
    /**
     * Constructs a new DuplicateEntryException with the specified detail message.
     *
     * @param message The detail message to be printed when an DuplicateEntryException exception occurs.
     */
    public DuplicateEntryException(String message) {
        super(message);
    }
}
