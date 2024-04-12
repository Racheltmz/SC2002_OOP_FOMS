package utils;

import java.util.Scanner;

// Scanner
public class InputScanner {
    private Scanner reader;
    private static InputScanner singleton = null;

    private InputScanner() {
        reader = new Scanner(System.in);
        reader.useDelimiter("\\n");
    }

    public static InputScanner getInstance() {
        if (singleton == null) {
            singleton = new InputScanner();
        }
        return singleton;
    }

    public int nextInt() {
        return reader.nextInt();
    }

    public double nextDouble() {
        return reader.nextDouble();
    }

    public String next() {
        return reader.next();
    }

    public String nextLine() {
        return reader.nextLine();
    }

    public void close() {
        reader.close();
    }
}
