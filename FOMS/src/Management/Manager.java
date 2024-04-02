package Management;

import Initialisation.InputScanner;
import Menu.Item;
import Menu.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static Authorisation.Authorisation.authoriseManager;
import static Initialisation.InputScanner.getInstance;
import static Management.EmployeeView.displayStaffByBranch;
import static Validation.ValidateDataType.*;

public class Manager extends Staff  {
    // Constructor
    public Manager(String staffID, String name, StaffRoles role, char gender, int age, String branch) {
        super(staffID, name, role, gender, age, branch);
    }

    /* STAFF MANAGEMENT PURPOSES */
    public static String queryBranch(Company company) {
        InputScanner sc = getInstance();
        // Display branches
        company.displayBranches();
        // Get branches
        ArrayList<Branch> branches = company.getBranches();
        // Handle invalid branch names by checking if index out of bounds
        int branchIndex;
        String branchName = null;
        boolean success = false;
        do {
            try {
                // Get user's selection
                System.out.println("Select Branch: ");
                branchIndex = sc.nextInt();
                branchName = branches.get(branchIndex - 1).getBranchName();
                success = true;
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Invalid value, please enter again.");
            }
        } while (!success);
        return branchName;
    }

    // Display staff list by branch Test Case 11
    public void displayStaffList(Company company, StaffRoles auth) {
        if (authoriseManager(auth)) {
            // Display staff info from branches
            String branch = queryBranch(company);
            displayStaffByBranch(company.filterBranch(branch), branch);
        }
    }

    /* MENU ITEM PURPOSES */
    // Add menu item
    public void addMenuItem(Company company, StaffRoles auth) throws InputMismatchException {
        if (authoriseManager(auth)) {
            InputScanner sc = getInstance();
            // Get menu by branch
            String branch = queryBranch(company);

            // Get item details
            System.out.println("Enter name: ");
            String name = sc.next();
            double price = validateDouble("Enter price ($): ");
            System.out.println("Enter category: ");
            String category = sc.next();
            company.getMenu(branch).addItem(new Item(name, price, branch, category));
        }
    }

    // Update menu item
    public void updateMenuItem(Company company, StaffRoles auth) {
        if (authoriseManager(auth)) {
            InputScanner sc = getInstance();
            // Get menu by branch
            String branch = queryBranch(company);
            Menu branchMenu = company.getMenu(branch);
            // Display items in branch
            branchMenu.displayMenuItems();
            boolean success = false;
            do {
                try {
                    // Get item to update by name
                    int itemIndex = validateInt("Enter the number of the item you want to update: ") - 1;
                    System.out.println(itemIndex);
                    Item itemToUpdate = branchMenu.getMenuItems().get(itemIndex);
                    // Update price and description
                    double price = validateDouble("Enter price ($): ");
                    System.out.println("Enter description: ");
                    String description = sc.next();
                    branchMenu.updateItem(itemToUpdate, price, description);
                    success = true;
                    System.out.println("Item successfully updated in menu!");
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    System.out.println("Please enter a valid number.");
                }
            } while (!success);
        }
    }

    // Remove menu item
    public void removeMenuItem(Company company, StaffRoles auth) {
        if (authoriseManager(auth)) {
            // Get menu by branch
            String branch = queryBranch(company);
            Menu branchMenu = company.getMenu(branch);
            // Display items in branch
            branchMenu.displayMenuItems();

            boolean success = false;
            do {
                try {
                    // Get item to remove by name
                    int itemIndex = validateInt("Enter the number of the item you want to remove: ") - 1;
                    Item itemToRmv = branchMenu.getMenuItems().get(itemIndex);
                    branchMenu.removeItem(itemToRmv);
                    success = true;
                    System.out.println("Item successfully removed from menu!");
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    System.out.println("Please enter a valid number.");
                }
            } while (!success);
        }
    }
}
