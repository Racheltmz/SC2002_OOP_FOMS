package exceptions;

/**
 * The InvalidStaffQuotaException class represents an exception that occurs when a staffQuota that is out of the allowed range is entered.
 * This exception is thrown when an Admin attempts to open a new branch with an invalid staffQuota.
 */
public class InvalidStaffQuotaException extends Exception {
   /**
     * Constructs a new InvalidStaffQuotaException with the specified detail message.
     *
     * @param message The detail message to be printed when an InvalidStaffQuotaException exception occurs.
     */
    public InvalidStaffQuotaException(String message) {
        super(message);
    }
}
