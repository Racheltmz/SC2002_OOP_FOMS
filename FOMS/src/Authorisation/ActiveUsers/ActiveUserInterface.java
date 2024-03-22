package Authorisation.ActiveUsers;

import Management.Company;
import Management.Staff;

import java.util.Scanner;

public interface ActiveUserInterface {
    // public void displayRoleInfo()
    public Staff getActiveStaff();
    public void logout();
    public void processMenu(Scanner sc, Company company);
}
