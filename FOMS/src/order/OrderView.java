package order;

import menu.MenuItem;

public class OrderView {
    // Receipt information for customer
    public static void printReceipt(Order order) {
        System.out.println("Your order ID is " + order.getOrderID());
        System.out.println("Your order is: ");
        for (MenuItem item : order.getItems()) {
            System.out.println("- " + item.getName());
        }
        System.out.println("Your customisations are: ");
        for (String customization : order.getCustomisation()) {
            System.out.println("- " + customization);
        }
        if (order.isTakeaway()) {
            System.out.println("You chose to takeaway");
        } else {
            System.out.println("You chose to dine in");
        }
        System.out.println("Thank you for shopping at " + order.getBranch());
    }

    // Print order details for OrderQueue
    public static void printOrderDetails(Order order) {
        System.out.println("Your order ID is " + order.getOrderID());
        System.out.println("Items:");
        for (MenuItem item : order.getItems()) {
            System.out.println("- " + item.getName());
        }
        System.out.println("Customisations: ");
        for (String customization : order.getCustomisation()) {
            System.out.println("- " + customization);
        }
        System.out.print("Takeaway or Dine In: ");
        if (order.isTakeaway()) {
            System.out.println("Takeaway");
        } else {
            System.out.println("Dine In");
        }
    }
}
