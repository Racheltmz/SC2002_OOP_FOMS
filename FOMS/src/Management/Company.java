package Management;

import Management.Staff.Roles;
import Menu.Item;
import Menu.Menu;
import Payment.Payment;

import java.util.ArrayList;
import java.util.Objects;

import static Authorisation.Authorisation.*;

// Information about the company such as staff, branch, payment list
public class Company {
    private ArrayList<Staff> staffList;
    private ArrayList<Branch> branches;
    private ArrayList<Payment> paymentList;
    private ArrayList<Menu> menuList;

    // Constructor for Company class
    public Company(ArrayList<Staff> staffList, ArrayList<Branch> branches, ArrayList<Payment> paymentList, ArrayList<Menu> menuList) {
        this.staffList = staffList;
        this.branches = branches;
        this.paymentList = paymentList;
        this.menuList = menuList;
    }

    /* STAFF PURPOSES */
    // Get staff based on staff ID
    public Staff getStaff(String staffId) {
        // Return staff object if it can be found
        for (int i = 0; i < this.staffList.size(); i++) {
            Staff curStaff = this.staffList.get(i);
            if (Objects.equals(curStaff.getStaffID(), staffId))
                return curStaff;
        }
        // Return null if staff cannot be found
        return null;
    }

    // Add staff (admin purposes)
    public void addStaff(Staff staff, Roles auth) {
        if (authoriseAdmin(auth))
            this.staffList.add(staff);
    }

    // Remove staff (admin purposes) (TO CHECK)
    public void rmvStaff(String staffID, Roles auth) {
        if (authoriseAdmin(auth))
            this.staffList.remove(this.getStaff(staffID));
    }

    // Filter list of staff members for strings (only branch field)
    public ArrayList<Staff> getStaffList(String filter, String filterVal, Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        boolean isAuth = authoriseManager(auth) || authoriseAdmin(auth);
        if (isAuth) {
            // Check if the filter is "branch", else it returns an empty array
            if (Objects.equals(filter, "branch")) {
                for (Staff staff : this.staffList) {
                    if (Objects.equals(staff.getBranch(), filterVal))
                        filteredStaff.add(staff);
                }
            }
        }
        return filteredStaff;
    }

    // Filter list of staff members for roles enum (only role field)
    public ArrayList<Staff> getStaffList(String filter, Roles filterVal, Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        if (authoriseAdmin(auth)) {
            // Check if the filter is "role", else it returns an empty array
            if (Objects.equals(filter, "role")) {
                for (Staff staff : this.staffList) {
                    if (staff.getRole() == filterVal)
                        filteredStaff.add(staff);
                }
            }
        }
        return filteredStaff;
    }

    // Filter list of staff members for character (only gender field)
    public ArrayList<Staff> getStaffList(String filter, char filterVal, Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        if (authoriseAdmin(auth)) {
            // Check if the filter is "gender", else it returns an empty array
            if (Objects.equals(filter, "gender")) {
                for (Staff staff : this.staffList) {
                    if (staff.getGender() == filterVal)
                        filteredStaff.add(staff);
                }
            }
        }
        return filteredStaff;
    }

    // Filter list of staff members for integer (only age field)
    public ArrayList<Staff> getStaffList(String filter, int filterVal, Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        if (authoriseAdmin(auth)) {
            // Check if the filter is "age", else it returns an empty array
            if (Objects.equals(filter, "age")) {
                for (Staff staff : this.staffList) {
                    if (staff.getAge() == filterVal)
                        filteredStaff.add(staff);
                }
            }
        }
        return filteredStaff;
    }

    // Get number of managers to check quota
    public int getNumManagers(Roles auth) {
        ArrayList<Staff> managerList = getStaffList("role", Roles.MANAGER, auth);
        return managerList.size();
    }

    /* BRANCHES PURPOSES */
    // Get branches (to display for customers)
    public ArrayList<Branch> getBranches() {
        return this.branches;
    }

    // Get branch based on branch name
    public Branch getBranchByName(String name) {
        // Return staff object if it can be found
        for (int i = 0; i < this.branches.size(); i++) {
            Branch branch = this.branches.get(i);
            if (Objects.equals(branch.getBranchName(), name))
                return branch;
        }
        // Return null if branch cannot be found
        return null;
    }

    // Display branches
    public void displayBranches() {
        System.out.println("Branches (enter the number): ");
        for (int i = 0; i < this.branches.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.branches.get(i).getBranchName());
        }
    }

    // Add branch (admin purposes)
    public void addBranch(Branch branch, Roles auth) {
        if (authoriseAdmin(auth))
            this.branches.add(branch);
    }

    // Remove branch (admin purposes)
    public void rmvBranch(String branchName, Roles auth) {
        if (authoriseAdmin(auth))
            this.branches.remove(this.getBranchByName(branchName));
    }

    /* PAYMENT PURPOSES */
    // Get all payment methods
    public ArrayList<Payment> getPaymentList() {
        return this.paymentList;
    }

    // Get payment based on payment method
    public Payment getPaymentMtd(String method) {
        // Return payment object if it can be found
        for (int i = 0; i < this.paymentList.size(); i++) {
            Payment payment = this.paymentList.get(i);
            if (Objects.equals(payment.getPaymentMethod(), method))
                return payment;
        }
        // Return null if payment cannot be found
        return null;
    }

    // Add payment method (admin purposes)
    public void addPaymentMtd(Payment payment, Roles auth) {
        if (authoriseAdmin(auth))
            this.paymentList.add(payment);
    }

    // Remove payment method (admin purposes)
    public void rmvPaymentMtd(String paymentMtd, Roles auth) {
        if (authoriseAdmin(auth))
            this.paymentList.remove(this.getPaymentMtd(paymentMtd));
    }

    /* MENU PURPOSES */
    // Get menu by branch name
    public Menu getMenu(String branchName) {
        // Return staff object if it can be found
        for (int i = 0; i < this.menuList.size(); i++) {
            Menu menu = this.menuList.get(i);
            if (Objects.equals(menu.getBranch(), branchName))
                return menu;
        }
        // Return null if menu cannot be found
        return null;
    }

    // Get number of menu items
    public int getNumMenuItems(String branch) {
        return getMenu(branch).getMenuItems().size();
    }
}
