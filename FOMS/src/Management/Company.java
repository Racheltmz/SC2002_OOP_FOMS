package Management;

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

    public ArrayList<Staff> getAllStaff() {
        return this.staffList;
    }

    // Add staff (admin purposes)
    public void addStaff(Staff staff, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.staffList.add(staff);
    }

    // Remove staff (admin purposes) (TO CHECK)
    public void rmvStaff(String staffID, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.staffList.remove(this.getStaff(staffID));
    }

    // Filters
    public ArrayList<Staff> filterBranch(String branch) {
        StaffList toFilter = new StaffList();
        return toFilter.getStaffBranch(this.staffList, branch);
    }

    public ArrayList<Staff> filterRole(StaffRoles role) {
        StaffList toFilter = new StaffList();
        return toFilter.getStaffRole(this.staffList, role);
    }

    // Get number of managers to check quota
    public int getNumManagers(StaffRoles auth) {
        if (authoriseAdmin(auth))
            return this.filterRole(StaffRoles.MANAGER).size();
        return 0;
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
    public void addBranch(Branch branch, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.branches.add(branch);
    }

    // Remove branch (admin purposes)
    public void rmvBranch(String branchName, StaffRoles auth) {
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
    public void addPaymentMtd(Payment payment, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.paymentList.add(payment);
    }

    // Remove payment method (admin purposes)
    public void rmvPaymentMtd(String paymentMtd, StaffRoles auth) {
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
