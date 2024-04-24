package order;

import branch.BranchDirectory;
import io.OrderXlsxHelper;
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
        BranchDirectory branchDirectory = BranchDirectory.getInstance();

        System.out.println("==================================");
        System.out.println("|              Menu              |");
        System.out.println("|        Welcome Customer!       |");
        System.out.println("==================================");

        boolean exit = false;
        do {
            int customerChoice = validateIntRange("1. Browse Menu\n2. Check Order Status\n3. Collect Order\n4. Return back\nPlease select option: ", 1, 4);
            System.out.println();

            switch (customerChoice) {
                case 1:
                    // Ask customer to select branch
                    String branch = branchDirectory.getBranchByUserInput().getName();
                    System.out.println();

                    // Menu Browsing
                    menuDirectory.displayMenuByBranch(branch);
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
                    boolean customise = true;
                    do {
                        System.out.println("Selected Items:");
                        for (int i = 0; i < size; i++) {
                            System.out.println((i + 1) + ". " + selectedItems.get(i).getName());
                        }
                        int customisationOption = validateIntRange(
                                ("Which item would you like to customise? (enter " + (size + 1) + " to quit): "), 1,
                                size + 1);
                        if (customisationOption == size + 1) {
                            customise = false;
                            break;
                        }
                        MenuItem itemToCustomise = selectedItems.get(customisationOption - 1);
                        String customisationInput = validateString("What customisation would you like? ");
                        customisations.add(itemToCustomise.getName() + " : " + customisationInput);
                        System.out.println();
                    } while (customise);

                    // Dining Options
                    boolean takeaway = false;
                    System.out.println();
                    int input = validateIntRange("1. Takeaway\n2. Dine-In\nPlease select pickup option: ", 1, 2);
                    if (input == 1) {
                        takeaway = true;
                    }
                    System.out.println();

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
                    // Ask customer to select branch
                    branch = branchDirectory.getBranchByUserInput().getName();
                    System.out.println();

                    // Check Order Status
                    orderQueue.getStatusById(branch);
                    System.out.println();
                    break;

                case 3:
                    OrderXlsxHelper orderXlsxHelper = OrderXlsxHelper.getInstance();

                    // Ask customer to select branch
                    branch = branchDirectory.getBranchByUserInput().getName();
                    System.out.println();

                    // Display ready orders
                    ArrayList<Order> readyOrders = orderQueue.displayReadyOrders(branch);
                    if (!(readyOrders == null)) {
                        int listSize = readyOrders.size();
                        int selectOrder = validateIntRange("Please select which order to pickup: ", 1, listSize);
                        Order orderToPickup = readyOrders.get(selectOrder - 1);
                        orderToPickup.setStatus(OrderStatus.COMPLETED);
                        orderXlsxHelper.updateXlsx(orderToPickup);
                        System.out.println("Order completed. Thank you for dining with us!");
                    }
                    break;

                case 4:
                    // Exit customer interface
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
