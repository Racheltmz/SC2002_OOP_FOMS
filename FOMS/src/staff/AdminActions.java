package staff;

import branch.Branch;
import management.Company;
import payment.Payment;
import payment.PaymentManager;
import payment.PaymentService;
import utils.InputScanner;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import static staff.StaffRoles.*;
import static staff.StaffRoles.ADMIN;
import static utils.InputScanner.getInstance;
import static validation.ValidateDataType.validateInt;
import static validation.ValidateDataType.validateOption;

public class AdminActions {
    public static void addPaymentMethod()
    {
        PaymentManager.addPaymentMethod(ADMIN);
    }

    public static void removePaymentMethod()
    {
        PaymentManager.removePaymentMethod(ADMIN);
    }

    public static void displayPaymentMethods()
    {
        PaymentManager.displayPaymentMethods();
    }
    protected static boolean setRole(Company company, String staffID, String branch) {
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        Branch Branch = company.getBranchDirectory().getBranchByName(branch);

        if (staff != null && branch != null) {
            if (staff.getBranch().equals(branch)) {
                staff.setRole(MANAGER);
                return true;
            } else {
                System.out.println("This staff member does not belong to this branch.");
                return false;
            }
        } else if (staff == null && branch != null) {
            System.out.println("Staff member not found.");
            return false;
        } else if (branch == null && staff != null) {
            System.out.println("Branch not found.");
            return false;
        } else {
            System.out.println("Branch and staff member not found.");
            return false;
        }
    }

    protected static boolean setBranch(Company company, String staffID, String branch) {
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        Branch Branch = company.getBranchDirectory().getBranchByName(branch);
        if (staff != null && Branch != null) {
            if (staff.getBranch().equals(branch)) {
                staff.setBranch(branch);
                return true;
            } else {
                System.out.println("This staff member does not belong to this branch");
                return false;
            }
        } else if (staff == null && branch != null) {
            System.out.println("Staff member not found.");
            return false;
        } else if (branch == null && staff != null) {
            System.out.println("Branch not found.");
            return false;
        } else {
            System.out.println("Branch and staff member not found.");
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
        boolean notExisting = false;
        while (!notExisting) {
            System.out.print("Enter branch name: ");
            String branchName = sc.nextLine();
            Branch existingBranch = company.getBranchDirectory().getBranchByName(branchName);
            if (existingBranch == null) {
                notExisting = true;
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
                Branch branch = new Branch(branchName, location, quota);
                company.getBranchDirectory().addBranch(branch, ADMIN);
                System.out.printf("New branch created. Its details are:\nName: %s\nStaff quota: %d\nManager Quota: %d\nLocation: %s\n", branchName, quota, branch.getManagerQuota(), location);
                sc.close();
            } else {
                System.out.print("Branch name already exists!");
            }
        }
    }

    protected static void closeBranch(Company company) {
        InputScanner sc = getInstance();
        System.out.println("Enter branch name: ");
        String branchName = sc.next();
        branchName += sc.nextLine();

        Branch existingBranch = company.getBranchDirectory().getBranchByName(branchName);
        boolean exists = false;

        while (!exists) {
            if (existingBranch != null) {
                exists = true;
                ArrayList<Branch> branches = company.getBranchDirectory().getBranchDirectory();
                ArrayList<Staff> staff = company.getStaffDirectory().getStaffDirectory();

                Iterator<Branch> branchIterator = branches.iterator();
                while (branchIterator.hasNext()) {
                    Branch branch = branchIterator.next();
                    if (branch.getBranchName().equals(branchName)) {
                        for (Staff eachStaff : staff) {
                            if (eachStaff.getBranch().equals(branchName)) {
                                company.getStaffDirectory().rmvStaff(eachStaff.getStaffID(), ADMIN);
                            }
                        }
                        branchIterator.remove();
                        System.out.println("Branch " + branchName + "has been closed.");
                        sc.close();
                        return;
                    }
                }
            }
            System.out.println("Branch " + branchName + " is not found. Please re-enter branch name.");
            branchName = sc.next();
            branchName += sc.nextLine();
        }
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
                return;
            }
            else {
                System.out.println("Cannot assign manager.");
                return;
            }
        } else {
            System.out.println("Cannot assign managers. Quota reached for Branch. \n");
            return;
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
                    else {
                        System.out.println("Unable to promote staff.");
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
            if (staff.getRole() == ADMIN)
            {
                System.out.println("Unable to tranfer admin.");
                return;
            }
            //String currentBranch = staff.getBranch();
            boolean newBranchExists = false;
            Branch branch = company.getBranchDirectory().getBranchByName(Branch);
            if (branch != null) {
                newBranchExists = true;
            }
            if (newBranchExists) {
                if (setBranch(company, staffID, Branch)) {
                    System.out.println("Staff member with ID " + staffID + " has been transferred to " + Branch + " successfully");
                    return;
                }
                else {
                    System.out.println("Unable to transfer staff.");
                    return;
                }
            } else {
                System.out.println("This branch does not exist");
                return;
            }
        } else {
            System.out.println("Staff member with ID " + staffID + " not found");
            return;
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

    protected static void staffFiltering(Company company)
    {
        InputScanner sc = getInstance();
        StaffList filtering = new StaffList();
        ArrayList<Staff> filteredList;
        int filterOption = validateOption(("Please select option for filter:\n1. Branch\n2. Role\n3. Gender\n4. Age\n5. Return to main menu\n"), 1, 5);
        switch(filterOption)
        {
            case 1:
                System.out.println("Current branches:\n" + company.getBranchDirectory() + "\nEnter name of branch you wish to filter staff by: ");
                boolean found = false;
                while (!found)
                {
                    String branchName = sc.next();
                    branchName += sc.nextLine();
                    Branch branch = company.getBranchDirectory().getBranchByName(branchName);
                    if (branchName != null)
                    {
                        found = true;
                        filteredList = filtering.getStaffBranch(company.getStaffDirectory().getStaffDirectory(), branchName);
                        System.out.println("Filtered list of staff:\n" + filteredList);
                        break;
                    }
                    System.out.println("Branch not found! Please re-enter name: ");
                }
                break;
            case 2:
                switch (validateOption("Enter which role you wish to filter staff by:\n1. Staff\n2. Manager\n3. Admin\n4. Return to main menu\n",1, 4))
                {
                    case 1:
                        filteredList = filtering.getStaffRole(company.getStaffDirectory().getStaffDirectory(), STAFF);
                        System.out.println("Filtered list of staff:\n" + filteredList);
                        break;
                    case 2:
                        filteredList = filtering.getStaffRole(company.getStaffDirectory().getStaffDirectory(), MANAGER);
                        System.out.println("Filtered list of staff:\n" + filteredList);
                        break;
                    case 3:
                        filteredList = filtering.getStaffRole(company.getStaffDirectory().getStaffDirectory(), ADMIN);
                        System.out.println("Filtered list of staff:\n" + filteredList);
                        break;
                    case 4:
                        break;
                }
                break;
            case 3:
                switch(validateOption("Enter which gender you wish to filter staff by:\n1. Female\n2. Male\n3. Return to main menu",1, 3))
                {
                    case 1:
                        filteredList = filtering.getStaffGender(company.getStaffDirectory().getStaffDirectory(), 'F');
                        System.out.println("Filtered list of staff:\n" + filteredList);
                        break;
                    case 2:
                        filteredList = filtering.getStaffGender(company.getStaffDirectory().getStaffDirectory(), 'M');
                        System.out.println("Filtered list of staff:\n" + filteredList);
                        break;
                    case 3:
                        break;
                }
                break;
            case 4:
                boolean isFound = false;
                while (!isFound)
                {
                    int age = validateInt("Enter age you wish to filter staff by: ");
                    for (Staff staff : company.getStaffDirectory().getStaffDirectory())
                    {
                        if (staff.getAge() == age)
                        {
                            isFound = true;
                            filteredList = filtering.getStaffAge(company.getStaffDirectory().getStaffDirectory(), age);
                            System.out.println("Filtered list of staff:\n" + filteredList);
                            break;
                        }
                    }
                    System.out.println("No existing staff of this age. Please re-enter: ");
                }
                break;
            case 5:
                break;
        }
    }
}
