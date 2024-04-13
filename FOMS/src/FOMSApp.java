import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;

import authorisation.*;
import exceptions.EmptyListException;
import menu.MenuDirectory;
import payment.PaymentLoader;
import payment.PaymentManager;
import utils.InputScanner;
import java.util.ArrayList;
import java.util.Timer;
import management.Company;
import staff.Staff;
import staff.StaffRoles;
import order.OrderQueue;
import order.Order;
import order.OrderStatus;
import order.OrderTimerTask;
import menu.MenuItem;
import menu.Menu;
import payment.Payment;

import static authentication.Authentication.login;
import static branch.BranchDirectory.getBranchByUserInput;
import static menu.MenuDirectory.displayMenuByBranch;
import static utils.Initialisation.initialiseCompany;
import static utils.InputScanner.getInstance;
import static validation.ValidateDataType.validateOption;

// Main app file
public class FOMSApp {
    public static void main(String[] args) {
        // Initialise scanner
        InputScanner sc = getInstance();

        // Initialise company, order queue, payment;
        Company company = initialiseCompany();
        OrderQueue orderQueue = new OrderQueue();

        /* FOMS INTERFACE */
        // Iterate until system receives a valid input
        int choice = 0;
        do {
            System.out.println("==================================");
            System.out.println("|         Welcome to FOMS!       |");
            System.out.println("|         By FDAB Group 3        |");
            System.out.println("==================================");
            System.out.println("Please select option");
            choice = validateOption("1. Customer\n2. Staff\n3: Quit", 1, 3);

            switch (choice) {
                case 1:
                    /* CUSTOMER INTERFACE */
                    try {
                        System.out.println("==================================");
                        System.out.println("|              Menu              |");
                        System.out.println("|        Welcome Customer!       |");
                        System.out.println("==================================");
                        // Ask customer to select branch
                        String branchName = getBranchByUserInput(company.getBranchDirectory());

                        // Iterate until customer is done using functionalities
                        int customerChoice = 0;
                        do {
                            // Customer Options @gwen (test case 4-8, 18)
                            System.out.println("Please select option");
                            customerChoice = validateOption("1. Browse Menu\n2. Check Order Status\n3. Return back", 1, 3);

                            switch(customerChoice){
                                case 1:

                                    // Menu Browsing
                                    ArrayList<MenuItem> selectedItems = new ArrayList<>();
                                    Menu menu = company.getMenuDirectory().getMenu(branchName);
                                    MenuDirectory menuDirectory = company.getMenuDirectory();
                                    displayMenuByBranch(company, branchName);

                                    String itemName;
                                    boolean done = false;
                                    while (!done) {
                                        MenuItem selectedItem = menuDirectory.selectItem(branchName);
                                        if (selectedItem != null) {
                                            selectedItems.add(selectedItem);
                                        }
                                        else {
                                            done = true;
                                        }
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

                                    // Payment Information
                                    System.out.println("How would you like to pay?");
                                    PaymentManager.displayPaymentMethods();
                                    String paymentMethod = sc.next();
                                    sc.nextLine();

                                    // Order Processing and Payment
                                    Payment payment = new Payment(paymentMethod);
                                    Order order = new Order(branchName, selectedItems, customisations, takeaway, payment);
                                    orderQueue.addOrder(order);

                                    System.out.println("Total is " + order.calculateTotal());
                                    System.out.println("How much would you like to pay?");
                                    double customerPay = sc.nextDouble();

                                    // Verify Order and Payment
                                    order.placeOrder(customerPay);
                                    order.printReceipt();

                                    // Start Timer for Order Cancellation
                                    Timer timer = new Timer();
                                    // 5 Minutes in Milliseconds
                                    timer.schedule(new OrderTimerTask(timer,order),3000000);
                                    break;

                                case 2:
                                    // Check Order Status
                                    try{
                                        // Already asked for input inside
                                        OrderStatus status  = orderQueue.getStatusById();
                                        if (status == OrderStatus.READY){
                                            orderQueue.updateStatus(OrderStatus.READY, OrderStatus.COMPLETED);
                                        }
                                        else{
                                            System.out.println("Order is not ready for pickup.");
                                        }
                                    } catch (EmptyListException e){
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 3:
                                    System.out.println("Returning back to main menu.");
                                    break;
                            }
                        } while (customerChoice != 3);
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println("Please enter a valid integer only.\n");
                        sc.nextLine();
                    }
                    break;
                case 2:
                    /* STAFF INTERFACE */
                    // Initialise staff
                    ActiveFactory staffFactory = new ActiveFactoryStaff();
                    ActiveUser activeStaff = staffFactory.initInactive();
                    ActiveFactory managerFactory = new ActiveFactoryManager();
                    ActiveUser activeManager = managerFactory.initInactive();
                    ActiveFactory adminFactory = new ActiveFactoryAdmin();
                    ActiveUser activeAdmin = adminFactory.initInactive();
                    Staff staff;

                    // Iterate until user successfully logs in
                    int staffChoice = 0;
                    do {
                        // Login
                        if (activeStaff.getActiveStaff() == null
                                && activeManager.getActiveStaff() == null
                                && activeAdmin.getActiveStaff() == null) {
                            try {
                                System.out.println("==================================");
                                System.out.println("|          Login System          |");
                                System.out.println("|         Welcome Staff!         |");
                                System.out.println("==================================");
                                System.out.println("Please select option");
                                System.out.println("1. Login\n2. Return back");
                                staffChoice = sc.nextInt();
                                sc.nextLine(); // Consume newline character
                                if (staffChoice == 1) {
                                    try {
                                        staff = login(company.getStaffDirectory());
                                        // Set active staff
                                        if (staff != null) {
                                            if (staff.getRole() == StaffRoles.STAFF) {
                                                activeStaff = staffFactory.initActive(staff);
                                            } else if (staff.getRole() == StaffRoles.MANAGER) {
                                                activeManager = managerFactory.initActive(staff);
                                            } else if (staff.getRole() == StaffRoles.ADMIN) {
                                                activeAdmin = adminFactory.initActive(staff);
                                            }
                                        }
                                    } catch (NoSuchAlgorithmException e) {
                                        System.out.println("Unable to find algorithm.");
                                    }
                                } else if (staffChoice < 1 || staffChoice > 2)
                                    System.out.print("Invalid choice, please re-enter\n");
                            } catch (InputMismatchException inputMismatchException) {
                                System.out.println("Please enter a valid integer only.\n");
                                sc.nextLine();
                            }
                        } else {
                            try {
                                if (activeStaff.getActiveStaff() != null) {
                                    activeStaff.showOptions(company, orderQueue);
                                } else if (activeManager.getActiveStaff() != null) {
                                    activeManager.showOptions(company, orderQueue);
                                } else if (activeAdmin.getActiveStaff() != null) {
                                    activeAdmin.showOptions(company, orderQueue);
                                }
                            } catch (InputMismatchException inputMismatchException) {
                                System.out.println("Please choose a valid option.\n");
                                sc.nextLine();
                            }
                        }
                    } while (staffChoice != 2);
                    break;
                case 3:
                    break;
                default:
                    System.out.print("Invalid choice, please re-enter\n");
                    break;
            }
        } while (choice != 3);
        // Close scanner when the program terminates
        sc.close();
    }
}
