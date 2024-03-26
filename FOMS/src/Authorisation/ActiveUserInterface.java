package Authorisation;

import Management.Company;
import Management.Staff;
import Order.OrderQueue;

import java.util.Scanner;

public interface ActiveUserInterface {
    // Get active staff information
    Staff getActiveStaff();
    // Show options
    void showOptions(Scanner sc, Company company, OrderQueue queue);
    // Logout of account
    void logout();
}
