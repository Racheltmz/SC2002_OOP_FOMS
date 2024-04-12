package exceptions;

public class DuplicateEntryException extends Exception {
    public DuplicateEntryException() {
        super("Duplicate entry.");
    }
    public DuplicateEntryException(String msg) {
        super(msg);
    }
}
