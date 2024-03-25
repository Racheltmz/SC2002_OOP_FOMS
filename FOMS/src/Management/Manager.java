package Management;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Staff  {
    // Constructor
    public Manager(String staffID, String name, Roles role, char gender, int age, String branch) {
        super(staffID, name, role, gender, age, branch);
    }

    /* STAFF MANAGEMENT PURPOSES */
    // Display staff list by branch Test Case 11
    public void displayStaffList(Scanner sc, Company company, Roles auth) {
        // Display branches
        company.displayBranches();
        // Get branches
        ArrayList<Branch> branches = company.getBranches();
        // Handle invalid branch names by checking if index out of bounds
        while (true) {
            try {
                // Get user's selection
                System.out.println("Select Branch: ");
                int branchIndex = sc.nextInt();
                // Display branches
                ArrayList<Staff> staffByBranch = company.getStaffList("branch", branches.get(branchIndex-1).getBranchName(), auth);
                for (int i = 0; i < staffByBranch.size(); i++) {
                    staffByBranch.get(i).displayStaffDetails();
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid value, please enter again.");
            }
        }
    }

    /* MENU ITEM PURPOSES */
    // TODO: UNCOMMENT WHEN MENU AND ITEM CLASSES ARE CREATED
    // Add menu item
//    public void addMenuItem(Menu menu, String name, double price, String description, String category) {
//        Item menuItem = new Item(name, price, description, category);
//        menu.addItem(menuItem);
//    }

    // Update menu item
//    public void updateMenuItem(Menu menu, String name) {
//        menu.updateItem(name);
//    }

    // Remove menu item
//    public void removeMenuItem(Menu menu, String name) {
//        menu.removeItem(name);
//    }
}
