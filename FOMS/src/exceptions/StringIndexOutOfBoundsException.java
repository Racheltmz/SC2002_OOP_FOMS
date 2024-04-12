package exceptions;

public class StringIndexOutOfBoundsException extends Exception {
    public StringIndexOutOfBoundsException() {
        super("String index out of bounds.");
    }
    public StringIndexOutOfBoundsException(String msg) {
        super(msg);
    }
}
