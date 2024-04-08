package exceptions;

public class ArithmeticException extends Exception {
    public ArithmeticException() {
        super("Arithmetic Exception occurred.");
    }
    public ArithmeticException(String msg) {
        super(msg);
    }
}
