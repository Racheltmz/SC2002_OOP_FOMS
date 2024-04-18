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
import static utils.ValidateHelper.validateString;

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
            int customerChoice = validateIntRange("1. Browse Menu\n2. Check Order Status\n3. Return back", 1, 3, true);

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
                    int size = selectedItems.size();
                    ArrayList <String> customisations = new ArrayList<>();
                    boolean customise = true;
                    do {
                        for (int i = 0; i < size; i++)
                        {
                            System.out.println((i+1) + ". " + selectedItems.get(i).getName());
                        }
                        int customisationOption = validateIntRange(("Which item would you like to customise? (enter " + (size+1) + " to quit)"), 1, size + 1, true);
                        if (customisationOption == size+1)
                        {
                            customise = false;
                            break;
                        }
                        MenuItem itemToCustomise = selectedItems.get(customisationOption - 1);
                        String customisationInput = validateString("What customisation would you like?");
                        customisations.add(itemToCustomise.getName() + " : " + customisationInput);
                    } while (customise);

                    // Dining Options
                    boolean takeaway = false;
                    int input = validateIntRange("Please select pickup option:\n1. Takeaway\n2. Pickup", 1, 2, true);
                    if (input == 1) {
                        takeaway = true;
                    }

                    // Payment Information
                    Payment paymentMethod = Order.pay();

                    // Order Processing and Bill
                    Order order = new Order(branchName, selectedItems, customisations, takeaway, paymentMethod);
                    orderQueue.addOrder(order);

                    if (paymentMethod != null) {
                        // Verify Order and Payment
                        order.placeOrder();
                        order.printReceipt();

//                        // Start Timer for Order Cancellation
//                        Timer timer = new Timer();
//                        // 5 Minutes in Milliseconds
//                        timer.schedule(new OrderTimerTask(timer, order), 3000000);
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