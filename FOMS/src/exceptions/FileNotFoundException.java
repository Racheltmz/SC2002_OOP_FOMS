package exceptions;

public class FileNotFoundException extends Exception{
    public FileNotFoundException() {
        super("File not found.");
    }
    public FileNotFoundException(String msg) {
        super(msg);
    }
}
