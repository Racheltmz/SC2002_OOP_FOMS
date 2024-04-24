package exceptions;

/**
 * The AuthorizationException class represents an exception that occurs when the authorization of staff fails.
 * This exception is thrown when a staff member attempts to access a function without the proper authorization.
 */
public class AuthorizationException extends Exception {
    /**
     * Constructs a new AuthorizationException with the specified detail message.
     *
     * @param message The detail message to be printed when an AuthorizationException exception occurs.
     */
    public AuthorizationException(String message) {
        super(message);
    }
}
