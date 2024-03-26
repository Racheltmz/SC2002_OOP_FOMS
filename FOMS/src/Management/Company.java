package Management;

import Management.Staff.Roles;
import Payment.Payment;

import java.util.ArrayList;
import java.util.Objects;

import static Authorisation.Authorisation.*;

// Information about the company such as staff, branch, payment list
public class Company {
    private ArrayList<Staff> staffList;
    private ArrayList<Branch> branches;
    private ArrayList<Payment> paymentList;

    // Constructor for Company class
    public Company(ArrayList<Staff> staffList, ArrayList<Branch> branches, ArrayList<Payment> paymentList) {
        this.staffList = staffList;
        this.branches = branches;
        this.paymentList = paymentList;
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

    // Add staff
    public void addStaff(Staff staff, Roles auth) {
        if (authoriseAdmin(auth))
            this.staffList.add(staff);
    }

    // TODO: Remove staff

    // Filter list of staff members for strings (only branch field)
    public ArrayList<Staff> getStaffList(String filter, String filterVal, Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        boolean isAuth = authoriseManager(auth) || authoriseAdmin(auth);
        if (isAuth) {
            // Check if the filter is "branch", else it returns an empty array
            if (Objects.equals(filter, "branch")) {
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (Objects.equals(this.staffList.get(i).getBranch(), filterVal))
                        filteredStaff.add(this.staffList.get(i));
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
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (this.staffList.get(i).getRole() == filterVal)
                        filteredStaff.add(this.staffList.get(i));
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
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (this.staffList.get(i).getGender() == filterVal)
                        filteredStaff.add(this.staffList.get(i));
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
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (this.staffList.get(i).getAge() == filterVal)
                        filteredStaff.add(this.staffList.get(i));
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

    // TODO: Remove branch (admin purposes)
    // public void rmvBranch(String branchName, Roles auth) {
    //     AuthoriseAdmin authAdmin = new AuthoriseAdmin();
    //     if (authAdmin.authorise(auth))
    //         this.branches.remove(this.branches.indexOf(branchName));
    // }

    /* PAYMENT PURPOSES */
    // Get all payment methods
    public ArrayList<Payment> getPaymentList() {
        return this.paymentList;
    }

    // Add payment method
    public void addPaymentMtd(Payment payment, Roles auth) {
        if (authoriseAdmin(auth))
            this.paymentList.add(payment);
    }

    // TODO: Remove payment method
    // public void rmvPaymentMtd(String paymentMtd, Roles auth) {
    //     AuthoriseAdmin authAdmin = new AuthoriseAdmin();
    //     if (authAdmin.authorise(auth))
    //         this.paymentList.remove(this.paymentList.indexOf(paymentMtd));
    // }
}
