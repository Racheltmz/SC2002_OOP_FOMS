package order;

import menu.MenuDirectory;
import menu.MenuItem;
import payment.Payment;

import java.util.ArrayList;

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

        // Ask customer to select branch
        String branch = menuDirectory.displayMenuByBranch();

        boolean exit = false;
        do {
            System.out.println("Please select option");
            int customerChoice = validateIntRange("1. Browse Menu\n2. Check Order Status\n3. Return back", 1, 3);

            switch (customerChoice) {
                case 1:
                    // Menu Browsing
                    ArrayList<MenuItem> selectedItems = new ArrayList<>();

                    boolean done = false;
                    do {
                        MenuItem selectedItem = menuDirectory.selectItem(branch);
                        if (selectedItem != null) {
                            selectedItems.add(selectedItem);
                        } else {
                            if (selectedItems.isEmpty()) {
                                System.out.println("Please select at least one item.");
                            } else {
                                done = true;
                                System.out.println();
                            }
                        }
                    } while (!done);

                    // Customisations
                    int size = selectedItems.size();
                    ArrayList<String> customisations = new ArrayList<>();
                    while (true) {
                        System.out.println("Selected Items:");
                        for (int i = 0; i < size; i++) {
                            System.out.println((i + 1) + ". " + selectedItems.get(i).getName());
                        }
                        System.out.println();
                        int customisationOption = validateIntRange(
                                ("Which item would you like to customise? (enter " + (size + 1) + " to quit): "), 1,
                                size + 1);
                        if (customisationOption == size + 1) {
                            break;
                        }
                        MenuItem itemToCustomise = selectedItems.get(customisationOption - 1);
                        String customisationInput = validateString("What customisation would you like?");
                        customisations.add(itemToCustomise.getName() + " : " + customisationInput);
                    }

                    // Dining Options
                    boolean takeaway = false;
                    int input = validateIntRange("Please select pickup option:\n1. Takeaway\n2. Dine-In", 1, 2);
                    if (input == 1) {
                        takeaway = true;
                    }

                    // Payment Information
                    Payment payment = Order.pay();

                    if (payment != null) {
                        // Order Processing and Bill
                        Order order = new Order(branch, selectedItems, customisations, takeaway, payment.getPaymentMethod());
                        orderQueue.addOrder(order);
                        // Verify Order and Payment
                        order.placeOrder();
                        order.printReceipt();
                    }
                    break;

                case 2:
                    // Check Order Status
                    orderQueue.getStatusById(branch);
                    System.out.println();
                    break;

                case 3:
                    // Exit customer interface
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
