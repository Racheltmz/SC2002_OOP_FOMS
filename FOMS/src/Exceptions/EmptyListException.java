package Exceptions;

public class EmptyListException extends Exception {
    public EmptyListException() {
        super("List is empty. No records to display.");
    };
    public EmptyListException(String msg) {
        super(msg);
    }
}