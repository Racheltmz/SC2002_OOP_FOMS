package validation;

import management.Company;
import menu.Menu;
import menu.MenuItem;
import utils.InputScanner;

import java.util.InputMismatchException;

import static branch.BranchDirectory.getBranchByUserInput;
import static utils.InputScanner.getInstance;

public class ValidateDataType {
    //Check integer
    public static int validateInt(String msg) {
        InputScanner sc = getInstance();
        boolean success = false;
        int input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextInt();
                // TODO: Improve implementation of handling negative vals
                if (input >= 0)
                    success = true;
                else
                    System.out.println("Negative values are invalid.");
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }
    // Check option
    public static int validateOption(String msg, int initial, int end) {
        InputScanner sc = getInstance();
        boolean success = false;
        int input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextInt();
                if (input >= initial && input <= end)
                {
                    success = true;
                }
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid integer.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

    // Check double
    public static double validateDouble(String msg) {
        InputScanner sc = getInstance();
        boolean success = false;
        double input = 0;
        do {
            try {
                System.out.println(msg);
                input = sc.nextDouble();
                // TODO: Improve implementation of handling negative values
                if (input >= 0)
                    success = true;
                else
                    System.out.println("Negative values are invalid.");
            }  catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid float.\n");
                sc.nextLine();
            }
        } while (!success);
        return input;
    }

    public static void validateMenuItemUpdate(Company company) {
        String branch = getBranchByUserInput(company.getBranchDirectory());
        Menu branchMenu = company.getMenuDirectory().getMenu(branch);
        branchMenu.displayItems();

        InputScanner sc = getInstance();
        boolean success = false;
        do {
            try {
                // Get item to update by name
                int itemIndex = validateInt("Enter the number of the item you want to update: ") - 1;
                MenuItem itemToUpdate = branchMenu.getMenuItems().get(itemIndex);
                // Update price and description
                double price = validateDouble("Enter price ($): ");
                System.out.println("Enter description: ");
                String description = sc.next();
                description += sc.nextLine();
                branchMenu.updateItem(itemToUpdate, price, description);
                success = true;
                System.out.println("Item successfully updated in menu!");
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Please enter a valid number.");
            }
        } while (!success);
    }

    public static void validateMenuItemRemoval(Company company) {
        // Get menu by branch
        String branch = getBranchByUserInput(company.getBranchDirectory());
        Menu branchMenu = company.getMenuDirectory().getMenu(branch);
        // Display items in branch
        branchMenu.displayItems();

        boolean success = false;
        do {
            try {
                // Get item to remove by name
                int itemIndex = validateInt("Enter the number of the item you want to remove: ") - 1;
                MenuItem itemToRmv = branchMenu.getMenuItems().get(itemIndex);
                branchMenu.removeItem(itemToRmv);
                success = true;
                System.out.println("Item successfully removed from menu!");
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Please enter a valid number.");
            }
        } while (!success);
    }

    public static void validateMenuItemAddition(Company company) {
        InputScanner sc = getInstance();
        // Get menu by branch
        String branch = getBranchByUserInput(company.getBranchDirectory());
        // Get item details
        System.out.println("Enter name: ");
        String name = sc.next();
        double price = validateDouble("Enter price ($): ");
        System.out.println("Enter category: ");
        String category = sc.next();
        category += sc.nextLine();
        company.getMenuDirectory().getMenu(branch).addItem(new MenuItem(name, price, branch, category));
    }
}
