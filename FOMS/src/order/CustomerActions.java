package order;

import exceptions.EmptyListException;
import management.Company;
import menu.MenuDirectory;
import menu.MenuItem;
import payment.Payment;
import utils.InputScanner;

import java.util.ArrayList;
import java.util.Timer;

import static branch.BranchDirectory.getBranchByUserInput;
import static menu.MenuDirectory.displayMenuByBranch;
import static validation.ValidateDataType.validateOption;
import static utils.InputScanner.getInstance;

public class CustomerActions {
    public static void showOptions(Company company, OrderQueue orderQueue) {
        System.out.println("==================================");
        System.out.println("|              Menu              |");
        System.out.println("|        Welcome Customer!       |");
        System.out.println("==================================");
        // Ask customer to select branch
        String branchName = getBranchByUserInput(company.getBranchDirectory());

        boolean exit = false;
        do {
            InputScanner sc = getInstance();

            // Customer Options @gwen (test case 4-8, 18)
            System.out.println("Please select option");
            int customerChoice = validateOption("1. Browse Menu\n2. Check Order Status\n3. Return back", 1, 3);

            switch (customerChoice) {
                case 1:
                    // Menu Browsing
                    ArrayList<MenuItem> selectedItems = new ArrayList<>();
                    MenuDirectory menuDirectory = company.getMenuDirectory();
                    displayMenuByBranch(company, branchName);

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
                    System.out.println("What would you like to customise?");
                    String customisationInput = sc.nextLine();
                    sc.nextLine();
                    String[] customisations = customisationInput.split(",");

                    // Dining Options
                    System.out.println("Are you dining in? Y/N");
                    sc.nextLine();
                    boolean takeaway = !sc.next().equalsIgnoreCase("Y");

                    // Order Processing and Bill
                    Order order = new Order(branchName, selectedItems, customisations, takeaway, null);
                    orderQueue.addOrder(order);
                    System.out.println("Total is " + order.calculateTotal());

                    // Payment Information
                    Payment paymentMethod = Order.pay(company);
                    order.setPaymentMtd(paymentMethod);

                    // Verify Order and Payment
                    order.placeOrder();
                    order.printReceipt();

                    // Start Timer for Order Cancellation
                    Timer timer = new Timer();
                    // 5 Minutes in Milliseconds
                    timer.schedule(new OrderTimerTask(timer, order), 3000000);
                    break;

                case 2:
                    // Check Order Status
                    try {
                        // Already asked for input inside
                        OrderStatus status = orderQueue.getStatusById();
                        if (status == OrderStatus.READY) {
                            orderQueue.updateStatus(OrderStatus.READY, OrderStatus.COMPLETED);
                        } else {
                            System.out.println("Order is not ready for pickup.");
                        }
                    } catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    // Exit customer interface
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
