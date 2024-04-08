package exceptions;

public class NullPointerException extends Exception {
    public NullPointerException() {
        super("Null Pointer Exception occurred.");
    }
    public NullPointerException(String msg) {
        super(msg);
    }
}
