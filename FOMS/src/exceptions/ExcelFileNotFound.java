package exceptions;

public class ExcelFileNotFound extends Exception {
    public ExcelFileNotFound() {
        super("Excel file not found.");
    }
    public ExcelFileNotFound(String msg) {
        super(msg);
    }
}
