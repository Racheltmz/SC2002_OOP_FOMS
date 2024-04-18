package utils;

import java.util.Scanner;

/**
 * Scanner to accept user inputs in the CLI.
 */
public class InputScanner {
    /**
     * Instantiate Scanner object.
     */
    private final Scanner reader;
    /**
     * Initialise Scanner singleton to null.
     */
    private static InputScanner singleton = null;

    /**
     * Construct input scanner.
     */
    private InputScanner() {
        reader = new Scanner(System.in);
        reader.useDelimiter("\\n");
    }

    /**
     * Get singleton instance.
     *
     * @return Singleton InputScanner.
     */
    public static InputScanner getInstance() {
        if (singleton == null) {
            singleton = new InputScanner();
        }
        return singleton;
    }

    /**
     * Get the next integer from user.
     *
     * @return Integer from user input.
     */
    public int nextInt() {
        return reader.nextInt();
    }

    /**
     * Get the next double from user.
     *
     * @return Double from user input.
     */
    public double nextDouble() {
        return reader.nextDouble();
    }

    /**
     * Get the next word from user.
     *
     * @return String from user input.
     */
    public String next() {
        return reader.next();
    }

    /**
     * Get the next line from user.
     *
     * @return String from user input.
     */
    public String nextLine() {
        return reader.nextLine();
    }

    /**
     * Close scanner resources.
     */
    public void close() {
        reader.close();
    }
}