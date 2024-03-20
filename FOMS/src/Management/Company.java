package Management;

import Authorisation.AuthoriseAdmin;
import Authorisation.AuthoriseManager;

import java.util.ArrayList;
import java.util.Objects;

public class Company {
    private ArrayList<Staff> staffList;
    private ArrayList<Branch> branches;
    // private ArrayList<Payment> paymentList; TODO: COMMENT OUT ONCE PAYMENT CLASS IS ADDED

    // Constructor for Company class
    public Company() {}

    public Company(ArrayList<Staff> staffList) {
        this.staffList = staffList;
    }

    /* STAFF PURPOSES */
    public ArrayList<Staff> getStaffList() {
        return this.staffList;
    }

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
    public ArrayList<Staff> getStaffList(String filter, String filterVal, Staff.Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        AuthoriseManager authManager = new AuthoriseManager();
        if (authAdmin.authorise(auth) || authManager.authorise(auth)) {
            // Check if the filter is "branch", else it returns an empty array
            if (filter == "branch") {
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (this.staffList.get(i).getBranch() == filterVal)
                        filteredStaff.add(this.staffList.get(i));
                }
            }
        }
        return filteredStaff;
    }

    // Filter list of staff members for roles enum (only role field)
    public ArrayList<Staff> getStaffList(String filter, Staff.Roles filterVal, Staff.Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth)) {
            // Check if the filter is "role", else it returns an empty array
            if (filter == "role") {
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (this.staffList.get(i).getRole() == filterVal)
                        filteredStaff.add(this.staffList.get(i));
                }
            }
        }
        return filteredStaff;
    }

    // Filter list of staff members for character (only gender field)
    public ArrayList<Staff> getStaffList(String filter, char filterVal, Staff.Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth)) {
            // Check if the filter is "gender", else it returns an empty array
            if (filter == "gender") {
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (this.staffList.get(i).getGender() == filterVal)
                        filteredStaff.add(this.staffList.get(i));
                }
            }
        }
        return filteredStaff;
    }

    // Filter list of staff members for integer (only age field)
    public ArrayList<Staff> getStaffList(String filter, int filterVal, Staff.Roles auth) {
        // Initialise new staff list
        ArrayList<Staff> filteredStaff = new ArrayList<Staff>();
        // Authorise staff else return empty list
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth)) {
            // Check if the filter is "age", else it returns an empty array
            if (filter == "age") {
                for (int i = 0; i < this.staffList.size(); i++) {
                    if (this.staffList.get(i).getAge() == filterVal)
                        filteredStaff.add(this.staffList.get(i));
                }
            }
        }
        return filteredStaff;
    }

    public int getNumManagers(Staff.Roles auth) {
        ArrayList<Staff> managerList = getStaffList("role", Staff.Roles.MANAGER, auth);
        return managerList.size();
    }

    /* BRANCHES PURPOSES */
    // Get branches (to display for customers)
    public ArrayList<Management.Branch> getBranches() {
        return this.branches;
    }

    // Add branch (admin purposes)
    public void addBranch(Management.Branch branch, Staff.Roles auth) {
        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
        if (authAdmin.authorise(auth))
            this.branches.add(branch);
    }

    // Remove branch (admin purposes)
//    public void deleteBranch(String branchName, Staff.Roles auth) {
//        AuthoriseAdmin authAdmin = new AuthoriseAdmin();
//        if (authAdmin.authorise(auth))
//            this.branches.remove(this.branches.indexOf(branchName));
//    }

    /* PAYMENT PURPOSES */
//    public ArrayList<Payment> getPaymentList() {
//        return this.paymentList;
//    }
}