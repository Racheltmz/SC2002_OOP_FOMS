package Initialisation;

import java.util.Scanner;

public class InputScanner {
    private Scanner reader;
    private static InputScanner singleton = null;
    private boolean alreadyClosed;

    private InputScanner() {
        alreadyClosed = false;
        reader = new Scanner(System.in);
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
        alreadyClosed = true;
        reader.close();
    }
}
