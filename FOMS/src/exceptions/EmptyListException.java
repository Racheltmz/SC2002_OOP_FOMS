package exceptions;

/**
 * The EmptyListException class represents an exception that occurs when there are no available MenuItems or Menus.
 * This exception is thrown when a customer attempts to make an order when there are no available MenuItems or Menus.
 */
public class EmptyListException extends Exception {
    /**
     * Constructs a new EmptyListException with the specified detail message.
     *
     * @param message The detail message to be printed when an EmptyListException exception occurs.
     */
    public EmptyListException(String message) {
        super(message);
    }
}