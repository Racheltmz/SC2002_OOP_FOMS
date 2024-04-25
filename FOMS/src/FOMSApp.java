import java.util.InputMismatchException;

import ui.CustomerActions;
import order.OrderQueue;
import ui.StaffActions;
import utils.InputScanner;

import static utils.ValidateHelper.validateIntRange;

/**
 * Main App File
 * @author Afreen, Gwen, Priya, Rachel, Sanjana
 */
public class FOMSApp {
    public static void main(String[] args) {
        // Initialise scanner
        InputScanner sc = InputScanner.getInstance();

        // Iterate until system receives a valid input
        int choice = 0;
        do {
            try {
                System.out.println("==================================");
                System.out.println("|       Welcome to Popeyes!      |");
                System.out.println("==================================");
                choice = validateIntRange("1. Customer\n2. Staff\n3: Quit\nPlease select option: ", 1, 3);
                System.out.println();

                switch (choice) {
                    case 1:
                        /* CUSTOMER INTERFACE */
                        CustomerActions.showOptions();
                        break;
                    case 2:
                        /* STAFF INTERFACE */
                        StaffActions.showOptions();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.print("Invalid choice, please re-enter\n");
                        break;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer only.\n");
                sc.nextLine();
            }
        } while (choice != 3);

        // Terminate all timers
        OrderQueue queue = OrderQueue.getInstance();
        queue.terminateTimers();

        // Close scanner when the program terminates
        sc.close();
    }
}