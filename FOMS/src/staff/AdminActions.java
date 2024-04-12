package staff;

import branch.Branch;
import management.Company;
import payment.Payment;
import utils.InputScanner;

import java.util.ArrayList;
import java.util.Iterator;

import static staff.StaffRoles.*;
import static staff.StaffRoles.ADMIN;
import static utils.InputScanner.getInstance;

public class AdminActions {
    protected static boolean setRole(Company company, String staffID, String branch) {
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        if (staff != null) {
            if (staff.getBranch().equals(branch)) {
                staff.setRole(StaffRoles.MANAGER);
                return true;
            } else {
                System.out.println("This staff member does not belong to this branch.");
                return false;
            }
        } else {
            System.out.println("Staff member not found.");
            return false;
        }
    }

    protected static boolean setBranch(Company company, String staffID, String branch) {
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        if (staff != null) {
            if (staff.getBranch().equals(branch)) {
                staff.setBranch(branch);
                return true;
            } else {
                System.out.println("This staff member does not belong to this branch");
                return false;
            }
        } else {
            System.out.println("Staff member not found");
            return false;
        }
    }

    protected static void setStaffID(Company company, String staffID, String newStaffID) {
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        if (staff != null) {
            staff.setStaffID(newStaffID);
            System.out.println("Staff ID updated successfully");
        } else {
            System.out.println("Staff member not found");
        }
    }

    protected static void addBranch(Company company) {
        InputScanner sc = getInstance();
        System.out.print("Enter branch name: ");
        String branchName = sc.nextLine();
        System.out.print("Enter staff quota: ");
        int quota = sc.nextInt();
        while (!(1 <= quota && quota <= 15)) {
            System.out.println("Invalid staff quota! Please re-enter.");
            System.out.print("Enter staff quota: ");
            quota = sc.nextInt();
        }
        System.out.print("Enter branch location: ");
        String location = sc.next();
        location += sc.nextLine();
        branch.Branch branch = new Branch(branchName, location, quota);
        company.getBranchDirectory().addBranch(branch, StaffRoles.ADMIN);
        System.out.printf("New branch created. Its details are:\nName: %s\nStaff quota: %d\nManager Quota: %d\nLocation: %s\n", branchName, quota, branch.getManagerQuota(), location);
        sc.close();
    }

    protected static void closeBranch(Company company, String Branch) {
        InputScanner sc = getInstance();
        System.out.println("Enter branch name: ");
        String branchName = sc.nextLine();

        ArrayList<Branch> branches = company.getBranchDirectory().getBranchDirectory();
        ArrayList<Staff> staff = company.getStaffDirectory().getStaffDirectory();

        Iterator<Branch> branchIterator = branches.iterator();
        while (branchIterator.hasNext())
        {
            Branch branch = branchIterator.next();
            if (branch.getBranchName().equals(branchName)) {
                for (Staff eachStaff : staff)
                {
                    if (eachStaff.getBranch().equals(branchName))
                    {
                        company.getStaffDirectory().rmvStaff(eachStaff.getStaffID(), ADMIN);
                    }
                }
                branchIterator.remove();
                System.out.println("Branch " + branchName + "has been closed.");
                sc.close();
                return;
            }
        }
        System.out.println("Branch " + branchName + " is not found.");
        sc.close();
    }


    protected static void assignManager(Company company, String staffId, String Branch) {
        //int numManagers = getNumManagers(branch);
        int numManagers = company.getStaffDirectory().getNumManagers(ADMIN);
        StaffList stafflist = new StaffList();
        int numStaff = stafflist.getStaffRole(company.getStaffDirectory().getStaffDirectory(), STAFF).size();

        int quota = 0;
        if (numStaff >= 9 && numStaff <= 15) {
            quota = 3;
        } else if (numStaff >= 5 && numStaff <= 8) {
            quota = 2;
        } else if (numStaff >= 1 && numStaff <= 4) {
            quota = 1;
        }

        if (numManagers < quota) {
            if (setBranch(company, staffId, Branch)) {
                System.out.println("Manager is assigned to the Branch: " + Branch);
            }
        } else {
            System.out.println("Cannot assign any more Managers. Quota reached for Branch. \n");
        }
    }

    protected static void promoteStaff(Company company, String staffID, String Branch) {
        Staff staff = company.getStaffDirectory().getStaff(staffID);

        if (staff != null) {
            StaffRoles currentRole = staff.getRole();

            switch (currentRole) {
                case STAFF:
                    if (setRole(company, staffID, Branch)) {
                        System.out.println("Staff member with ID " + staffID + " has been promoted to Manager");
                    }
                    break;
                case MANAGER:
                    System.out.println("Manager cannot be promoted.");
                    break;
                case ADMIN:
                    System.out.println("Admin cannot be promoted.");
                    break;
            }
        } else {
            System.out.println("Staff member with ID " + staffID + " not found");
        }
    }

    protected static void transferStaff(Company company, String staffID, String Branch) {
        Staff staff = company.getStaffDirectory().getStaff(staffID);

        if (staff != null) {
            //String currentBranch = staff.getBranch();

            boolean newBranchExists = false;
            for (Branch branch : company.getBranchDirectory().getBranchDirectory()) {
                if (branch.getBranchName().equals(Branch)) {
                    newBranchExists = true;
                    break;
                }
            }
            if (newBranchExists) {
                if (setBranch(company, staffID, Branch)) {
                    System.out.println("Staff member with ID " + staffID + " has been transferred to " + Branch + " successfully");
                }
            } else {
                System.out.println("This branch does not exist");
            }
        } else {
            System.out.println("Staff member with ID " + staffID + " not found");
        }
    }

    protected static String addPayment(Company company, String method) {
        Payment payment;
        switch (method) {
            case "Credit/Debit Card":
                payment = new Payment(method);
                company.getPaymentDirectory().addPaymentMtd(payment, ADMIN); //since only admins can add payment methods
                break;
            case "Paypal":
                payment = new Payment(method);
                company.getPaymentDirectory().addPaymentMtd(payment, ADMIN);
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
        return "Payment method added successfully";
    }

    protected static String removePayment(Company company, String method){
        if (company.getPaymentDirectory().getPaymentDirectory().isEmpty()) {
            return "No payment methods available to remove.";
        }

        for (Payment payment : company.getPaymentDirectory().getPaymentDirectory()) {
            if (payment.getPaymentMethod().equals(method)) {
                company.getPaymentDirectory().rmvPaymentMtd(payment.getPaymentMethod(), ADMIN); // Assuming removePaymentMtd method is available in Company class
                return "Payment method removed successfully.";
            }
        }
        // If payment method with specified method is not found
        return "Payment method not found.";
    }
}
