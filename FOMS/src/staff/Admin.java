package staff;

import management.Company;

public class Admin extends Staff {

    public Admin(String staffID, String name, StaffRoles role, char gender, int age, String branch) {
        super(staffID, name, role, gender, age, branch);
    }

    protected void setStaffID(Company company, String staffID, String newStaffID) {
        AdminActions.setStaffID(company, staffID, newStaffID);
    }

    protected void addBranch(Company company) {
        AdminActions.addBranch(company);
    }

    protected void closeBranch(Company company, String Branch) {
        AdminActions.closeBranch(company, Branch);
    }


    protected void assignManager(Company company, String staffId, String Branch) {
        AdminActions.assignManager(company, staffId, Branch);
    }

    protected void promoteStaff(Company company, String staffID, String Branch) {
        AdminActions.promoteStaff(company, staffID, Branch);
    }

    protected void transferStaff(Company company, String staffID, String Branch) {
        AdminActions.transferStaff(company, staffID, Branch);
    }

    protected String addPayment(Company company, String method) {
        return AdminActions.addPayment(company, method);
    }

    protected String removePayment(Company company, String method){
         return AdminActions.removePayment(company, method);
    }
}