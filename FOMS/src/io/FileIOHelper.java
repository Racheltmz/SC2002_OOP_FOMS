package io;

import java.io.*;

/**
 * Helper class for File IO to the XLSX files in the data folder
 * <p>
 * The data folder is where we will be storing the application data

 */
// TODO: review implementation of io
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
    public static boolean fileExists(String name) {
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
     */
    public static FileInputStream getFileInputStream(String name) {
        try {
            return new FileInputStream(getFile(name));
        } catch (IOException | NullPointerException e) {
            System.out.println("File does not exist. Please check the file path.");
        }
        return null;
    }

    /**
     * Get the output stream from the file
     *
     * @param name Filename with extension
     * @return FileOutputStream of the file
     */
    public static FileOutputStream getFileOutputStream(String name) {
        try {
            return new FileOutputStream(getFile(name), true); // TODO: check the append
        } catch (IOException e) {
            System.out.println("Unable to output record.");
        }
        return null;
    }
}
