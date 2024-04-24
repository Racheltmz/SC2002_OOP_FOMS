package order;

import menu.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

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
        String branchString = "| Thank you for shopping at " + order.getBranch() + "!";
        System.out.println(formatString(branchString, 39) + "|");
        System.out.println("|                                      |");
        System.out.println("========================================\n");
    }

    // Print order details for OrderQueue
    public static void printOrderDetails(Order order) {
        HashMap<String, String> orderDetails = new HashMap<>();
        System.out.println("-".repeat(100));
        System.out.println();
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Items:");

        ArrayList<String> customisations = order.getCustomisation();
        for (int i=0; i<order.getCustomisation().size(); i++) {
            String customisation = customisations.get(i);
            String menuItem = customisation.split(" : ")[0];
            String details = customisation.split(" : ")[1];
            orderDetails.put(menuItem, details);
        }

        ArrayList<MenuItem> items = order.getItems();
        for (int i=0; i<order.getItems().size(); i++) {
            String addDetails = "";
            String customDetails = orderDetails.get(items.get(i).getName());
            if (customDetails != null)
                addDetails = ", customisations: " + customDetails;
            System.out.println("- " + items.get(i).getName() + addDetails);
        }
        System.out.print("Pickup option: ");
        if (order.isTakeaway()) {
            System.out.println("Takeaway");
        } else {
            System.out.println("Dine In");
        }
        System.out.println("Status: " + order.getStatus());
        System.out.println();
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
