package order;

public class UniqueIdGenerator {
    private static long idCounter = 100;

    public static synchronized String generateUniqueID(boolean takeaway){
        String prefix = takeaway ? "T" : "D";
        return prefix + "-" + idCounter++;
    }
}
