package Authorisation.ActiveUsers;

import Management.Company;
import Management.Staff;

import java.util.Scanner;

public interface ActiveUserInterface {
    // public void displayRoleInfo()
    Staff getActiveStaff();
    void logout();
    void processMenu(Scanner sc, Company company);
}
