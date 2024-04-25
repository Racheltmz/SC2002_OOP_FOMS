package order;

/**
 * UniqueIdGenerator class generates a unique ID for incoming Orders.
 */
public class UniqueIdGenerator {
    private static long idCounter = 100;

    /**
     * Generates a random unique ID based on the pickup option of the Order.
     * If the pickup option is takeaway, the order ID begins with 'T', if the
     * pickup option is dine-in, the order ID begins with 'D'.
     *
     * @param takeaway The pickup option.
     * @return The generated unique ID in a String format.
     */
    public static synchronized String generateUniqueID(boolean takeaway){
        String prefix = takeaway ? "T" : "D";
        return prefix + "-" + idCounter++;
    }
}
