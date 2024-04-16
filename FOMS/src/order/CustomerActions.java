package order;

import branch.Branch;
import branch.BranchDirectory;
import exceptions.EmptyListException;
import menu.Menu;
import menu.MenuDirectory;
import menu.MenuItem;
import payment.Payment;
import payment.PaymentDirectory;
import utils.InputScanner;

import java.util.ArrayList;
import java.util.Timer;

import static utils.InputScanner.getInstance;
import static utils.ValidateHelper.validateIntRange;

public class CustomerActions {
    public static void showOptions() {
        MenuDirectory menuDirectory = MenuDirectory.getInstance();
        OrderQueue orderQueue = OrderQueue.getInstance();

        System.out.println("==================================");
        System.out.println("|              Menu              |");
        System.out.println("|        Welcome Customer!       |");
        System.out.println("==================================");

        boolean exit = false;
        do {
            InputScanner sc = InputScanner.getInstance();

            // Customer Options @gwen (test case 4-8, 18)
            System.out.println("Please select option");
            int customerChoice = validateIntRange("1. Browse Menu\n2. Check Order Status\n3. Return back", 1, 3);

            switch (customerChoice) {
                case 1:
                    // Menu Browsing
                    ArrayList<MenuItem> selectedItems = new ArrayList<>();
                    String branchName = menuDirectory.displayMenuByBranch();

                    boolean done = false;
                    while (!done) {
                        MenuItem selectedItem = menuDirectory.selectItem(branchName);
                        if (selectedItem != null) {
                            selectedItems.add(selectedItem);
                        } else {
                            done = true;
                        }
                    }

                    if (selectedItems.isEmpty()) {
                        break;
                    }

                    // Customisations
                    System.out.println("What customisations would you like? (enter quit to exit)");
                    String customisationInput = sc.nextLine();
                    do {
                        customisationInput += sc.nextLine();
                    } while (!customisationInput.equalsIgnoreCase("quit"));
                    String[] customisations = customisationInput.split(",");

                    // Dining Options
                    boolean takeaway = false;
                    int input = validateIntRange("Please select pickup option:\n1. Takeaway\n2. Pickup", 1, 2);
                    if (input == 1) {
                        takeaway = true;
                    }

                    // Order Processing and Bill
                    Order order = new Order(branchName, selectedItems, customisations, takeaway, null);
                    orderQueue.addOrder(order);
                    System.out.println("Total is " + order.calculateTotal());

                    // Payment Information
                    Payment paymentMethod = Order.pay();
                    if (paymentMethod != null) {
                        order.setPaymentMtd(paymentMethod);

                        // Verify Order and Payment
                        order.placeOrder();
                        order.printReceipt();

                        // Start Timer for Order Cancellation
                        Timer timer = new Timer();
                        // 5 Minutes in Milliseconds
                        timer.schedule(new OrderTimerTask(timer, order), 3000000);
                    }
                    break;

                case 2:
                    // Check Order Status
                    orderQueue.getStatusById();
                    break;

                case 3:
                    // Exit customer interface
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
