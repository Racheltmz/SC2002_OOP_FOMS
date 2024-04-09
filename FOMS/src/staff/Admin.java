package staff;

import java.util.Iterator;
import java.util.ArrayList;

import branch.Branch;
import management.Company;
import payment.Payment;
import utils.InputScanner;

import static utils.InputScanner.getInstance;

public class Admin extends Staff{
    public Admin(String staffID, String name, StaffRoles role, char gender, int age, String branch){
        super(staffID, name, role, gender, age, branch); 
    }

    public void setRole(Company company, String staffID, String branch){
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        if(staff != null){
            if(staff.getBranch().equals(branch)){
                System.out.println("Role updated successfully.");
            }
            else{
                System.out.println("This staff member does not belong to this branch.");
            }
        }
        else{
            System.out.println("Staff member not found.");
        }
    }

    public void setBranch(Company company, String staffID, String branch){
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        if(staff != null){
            if(staff.getBranch().equals(branch)){
                System.out.println("Branch updated successfully");
            }
            else{
                System.out.println("This staff member does not belong to this branch");
            }
        }
        else{
            System.out.println("Staff member not found");
        }
    }

    public void setStaffID(Company company, String staffID, String newStaffID){
        Staff staff = company.getStaffDirectory().getStaff(staffID);
        if(staff != null){
            staff.setStaffID(newStaffID);
            System.out.println("Staff ID updated successfully");
        }
        else{
            System.out.println("Staff member not found");
        }
    }

    protected void addBranch(String Branch){
        // Initialise scanner
        InputScanner sc = getInstance();
        //company calls admin for this 
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
        Branch branch = new Branch(branchName, location, quota);
        System.out.printf("New branch created. Its details are:\nName: %s\nStaff quota: %d\nManager Quota: %d\nLocation: %s\n", branchName, quota, branch.getManagerQuota(), location);
    }

    protected void closeBranch(Company company, String Branch){
        // Initialise scanner
        InputScanner sc = getInstance();
        System.out.println("Enter branch name: ");
        String branchName = sc.nextLine();

        System.out.println("Enter branch location: "); //should i add or just use branch name cause not much value added?
        String branchLocation = sc.next();

        ArrayList<Branch> branches = company.getBranchDirectory().getBranchDirectory();

        Iterator<Branch> iterator = branches.iterator();
        while(iterator.hasNext()){
            Branch branch = iterator.next();
            if(branch.getBranchName().equals(branchName)){
                iterator.remove();
                System.out.println("Branch " + branchName + " at " + branchLocation + " has been closed.");
                return;
            }
        }
        System.out.println("Branch " + branchName + " is not found.");
        //fire staff?
    }

    protected void assignManager(Company company, String staffId, String branchName){
        int numManagers = company.getStaffDirectory().getNumManagers(StaffRoles.ADMIN);

        int numStaff = company.getBranchDirectory().getBranchByName(branchName).getStaffQuota();

        int quota = 0; 
        if(numStaff >= 9 && numStaff <= 15){
            quota = 3;
        }
        else if(numStaff >= 5 && numStaff <= 8){
            quota = 2; 
        }
        else if(numStaff >= 1 && numStaff <= 4){
            quota = 1; 
        }

        if(numManagers < quota){
            System.out.println("Manager is assigned to the Branch: " + branchName);
        }
        else{
            System.out.println("Cannot assign any more Managers. Quota reached for Branch. \n");
        }
    }

    protected void promoteStaff(Company company, String staffID, String Branch){
        Staff staff = company.getStaffDirectory().getStaff(staffID);

        if(staff != null){
            StaffRoles currentRole = staff.getRole(); 

            switch(currentRole){
                case STAFF:
                    staff.setRole(StaffRoles.MANAGER);
                    System.out.println("Staff member with ID " + staffID + " has been promoted to Manager");
                    break; 
                case MANAGER:
                    System.out.println("Manager cannot be promoted");
                    break; 
                case ADMIN:
                    System.out.println("Admin cannot be promoted");
                    break; 
            }
        }
        else{
            System.out.println("Staff member with ID " + staffID + " not found");
        }
    }

    protected void transferStaff(Company company, String staffID, String Branch){
        Staff staff = company.getStaffDirectory().getStaff(staffID);

        if(staff != null){
            //String currentBranch = staff.getBranch(); 

            boolean newBranchExists = false; 
            for(Branch branch : company.getBranchDirectory().getBranchDirectory()){
                if(branch.getBranchName().equals(Branch)){
                    newBranchExists = true; 
                    break; 
                }
            }
            if(newBranchExists){
                staff.setBranch(Branch);
                System.out.println("Staff member with ID " + staffID + " has been transferred to " + Branch + " successfully");
            }
            else{
                System.out.println("This branch does not exist");
            }
        }
        else{
            System.out.println("Staff member with ID " + staffID + " not found");
        }
    }

    protected String addPayment(Company company, String method){
        Payment payment; 
        switch(method){
            case "Credit/Debit Card":
                payment = new Payment(method); 
                company.getPaymentDirectory().addPaymentMtd(payment, StaffRoles.ADMIN); //since only admins can add payment methods
                break; 
            case "Paypal":
                payment = new Payment(method); 
                company.getPaymentDirectory().addPaymentMtd(payment, StaffRoles.ADMIN);
                break; 
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + method);
        } 
        return "Payment method added successfully"; 
    } 

    // protected String removePayment(String method){
    //     if (company.getPaymentList().isEmpty()) {
    //         return "No payment methods available to remove.";
    //     }
        
    //     for (Payment payment : company.getPaymentList()) {
    //         if (payment.getMethod().equals(method)) {
    //             company.removePaymentMtd(payment, Roles.ADMIN); // Assuming removePaymentMtd method is available in Company class
    //             return "Payment method removed successfully.";
    //         }
    //     }
        
    //     // If payment method with specified method is not found
    //     return "Payment method not found.";
    // }
    }


