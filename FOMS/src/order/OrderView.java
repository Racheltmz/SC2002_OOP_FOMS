package order;

import menu.MenuItem;

public class OrderView {
    // Receipt information for customer
    public static void printReceipt(Order order) {
        System.out.println("========================================");
        System.out.println("|            Order Receipt             |");
        System.out.println("========================================");
        System.out.println("| Order ID: " + formatString(order.getOrderID(), 27) + "|");
        System.out.println("|--------------------------------------|");
        System.out.println("| Items:                               |");
        for (MenuItem item : order.getItems()) {
            System.out.println("|   - " + formatString(item.getName(), 33) + "|");
        }
        System.out.println("|                                      |");
        System.out.println("| Customisations:                      |");
        for (String customization : order.getCustomisation()) {
            System.out.println("|   - " + formatString(customization, 33) + "|");
        }
        System.out.println("|                                      |");
        String totalString = String.format("| Total: %.2f", order.calculateTotal());
        System.out.println(formatString(totalString, 39) + "|");
        System.out.println("|                                      |");
        if (order.isTakeaway()) {
            System.out.println("| Pickup option: Takeaway              |");
        } else {
            System.out.println("| Pickup option: Dine-in               |");
        }
        System.out.println("|                                      |");
        System.out.println("| Thank you for shopping at " + order.getBranch() + "!       |");
        System.out.println("|                                      |");
        System.out.println("========================================\n");
    }

    // Print order details for OrderQueue
    public static void printOrderDetails(Order order) {
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Items:");
            for (MenuItem item : order.getItems()) {
                System.out.println("- " + item.getName());
            }
            System.out.println();
            System.out.println("Customisations: ");
            for (String customization : order.getCustomisation()) {
                System.out.println("- " + customization);
            }
            System.out.println();
            System.out.print("Pickup option: ");
            if (order.isTakeaway()) {
                System.out.println("Takeaway");
            } else {
                System.out.println("Dine In");
            }
        }
        else {
            return;
        }
    }

    private static String formatString(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        } else {
            StringBuilder padded = new StringBuilder(str);
            while (padded.length() < maxLength) {
                padded.append(" ");
            }
            return padded.toString();
        }
    }
}
