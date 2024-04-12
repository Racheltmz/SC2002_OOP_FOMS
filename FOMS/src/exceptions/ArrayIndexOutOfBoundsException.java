package exceptions;

public class ArrayIndexOutOfBoundsException extends Exception {
    public ArrayIndexOutOfBoundsException() {
        super("Array index out of bounds.");
    }
    public ArrayIndexOutOfBoundsException(String msg) {
        super(msg);
    }
}
