package Management;

import Authorisation.AuthoriseAdmin;
import Authorisation.AuthoriseManager;
import Management.Staff.Roles;

import java.util.ArrayList;
import java.util.Objects;

public class Company {
    private ArrayList<Staff> staffList;
    private ArrayList<Branch> branches;
    // private ArrayList<Payment> paymentList; TODO: COMMENT OUT ONCE PAYMENT CLASS IS ADDED

    // Constructor for Company class
    public Company(ArrayList<Staff> staffList, ArrayList<Branch> branches) {
        this.staffList = staffList;
        this.branches = branches;
    }

    /* STAFF PURPOSES */
    public Staff getStaff(String staffId) {
        // Return staff object if it can be found
        for (int i=0; i < this.staffList.size(); i++) {
            Staff curStaff = this.staffList.get(i);
            if (Objects.equals(curStaff.getStaffID(), staffId))
                return curStaff;
        }
        // Return null if staff cannot be found
        return null;
    }

    // Filter list of staff members for strings (only branch field)
    public ArrayList<Staff> getStaffList(String filter, String filterVal, Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        AuthoriseManager authManager = new AuthoriseManager();
        boolean isAuth = authManager.authorise(auth) || authAdmin.authorise(auth);
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
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth)) {
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
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth)) {
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
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth)) {
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
        for (int i=0; i<this.branches.size(); i++) {
            System.out.printf("%d. %s\n", i+1, this.branches.get(i).getBranchName());
        }
    }

    // Add branch (admin purposes)
    public void addBranch(Branch branch, Roles auth) {
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth))
            this.branches.add(branch);
    }

    // Remove branch (admin purposes)
//    public void deleteBranch(String branchName, Roles auth) {
//        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
//        if (authAdmin.authorise(auth))
//            this.branches.remove(this.branches.indexOf(branchName));
//    }

    /* PAYMENT PURPOSES */
//    public ArrayList<Payment> getPaymentList() {
//        return this.paymentList;
//    }
}
