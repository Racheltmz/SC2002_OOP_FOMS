package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Helper class for File IO to the XLSX files in the data folder
 * <p>
 * The data folder is where we will be storing the application data

 */
public class FileIOHelper {

    /**
     * Ensure that the data folder exists. Otherwise, creates it.
     *
     * @return Folder file object
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File init() {
        File folder = new File("./data");
        if (!folder.exists()) folder.mkdir();
        return folder;
    }

    /**
     * Check if file exists
     *
     * @param name File Name
     * @return true if exist
     */
    public static boolean exists(String name) {
        File folder = init();
        File f = new File(folder.getAbsolutePath() + File.separator + name);
        return f.exists();
    }

    /**
     * Gets the file object in the data folder, creates the file if it doesn't exist
     *
     * @param name Filename with extension
     * @return File object if valid, null otherwise
     */
    public static File getFile(String name) {
        File folder = init();
        return new File(folder.getAbsolutePath() + File.separator + name);
    }

    /**
     * Get the input stream from the file
     *
     * @param name Filename with extension
     * @return FileInputStream of the file
     * @throws IOException File Not Found Exception
     */
    public static FileInputStream getFileInputStream(String name) throws IOException {
        return new FileInputStream(getFile(name));
    }

    /**
     * Get the output stream from the file
     *
     * @param name Filename with extension
     * @return FileOutputStream of the file
     * @throws IOException File Not Found Exception
     */
    public static FileOutputStream getFileOutputStream(String name) throws IOException {
        return new FileOutputStream(getFile(name));
    }

    /**
     * Get the Buffered Writer object from the file
     *
     * @param name filename with extension
     * @return Buffered Writer of the file
     * @throws IOException File Not Found Exception
     */
    public static BufferedWriter getFileBufferedWriter(String name) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(getFileOutputStream(name)));
    }

    // Get Excel spreadsheet by filename
    public static XSSFSheet getSheet(String filePath) {
        XSSFSheet sheet = null;
        try {
            // Reading file from local directory
            FileInputStream file = FileIOHelper.getFileInputStream(filePath);

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first sheet from the workbook
            sheet = workbook.getSheetAt(0);

            // Closing workbook and file output streams
            workbook.close();
            file.close();
        } catch (IOException ioException) {
            System.out.println("Error reading the Excel file: " + ioException.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sheet;
    }
}
