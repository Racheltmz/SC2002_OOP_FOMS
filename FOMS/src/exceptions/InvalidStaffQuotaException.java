package exceptions;

public class InvalidStaffQuotaException extends Exception {
    public InvalidStaffQuotaException() {
        super("String index out of bounds.");
    }
    public InvalidStaffQuotaException(String msg) {
        super(msg);
    }
}
