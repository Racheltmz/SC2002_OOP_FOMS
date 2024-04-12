package staff;

import management.Company;

public class Admin extends Staff {

    public Admin(String staffID, String name, StaffRoles role, char gender, int age, String branch) {
        super(staffID, name, role, gender, age, branch);
    }

    public static void displayPaymentMethods() { AdminActions.displayPaymentMethods(); }

    public void setStaffID(Company company, String staffID, String newStaffID) {
        AdminActions.setStaffID(company, staffID, newStaffID);
    }

    public static void staffFiltering(Company company) { AdminActions.staffFiltering(company); }

    public static void addBranch(Company company) {
        AdminActions.addBranch(company);
    }

    public static void closeBranch(Company company) {
        AdminActions.closeBranch(company);
    }


    public static void assignManager(Company company, String staffId, String Branch) {
        AdminActions.assignManager(company, staffId, Branch);
    }

    public static void promoteStaff(Company company, String staffID, String Branch) {
        AdminActions.promoteStaff(company, staffID, Branch);
    }

    public static void transferStaff(Company company, String staffID, String Branch) {
        AdminActions.transferStaff(company, staffID, Branch);
    }

    protected String addPayment(Company company, String method) {
        return AdminActions.addPayment(company, method);
    }

    protected String removePayment(Company company, String method){
         return AdminActions.removePayment(company, method);
    }
}