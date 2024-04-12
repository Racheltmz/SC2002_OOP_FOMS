package management;

import static authorisation.Authorisation.authoriseAdmin;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap; 
import java.util.Map;

import branch.Branch;
import branch.BranchDirectory;
import payment.Payment;
import payment.PaymentDirectory;
import staff.Staff;
import staff.StaffDirectory;
import staff.StaffRoles;

public class AdminActions { 
    private Map<String, StaffRoles> staffRolesMap = new HashMap<>();

    protected void addBranch(String Branch){
        //company calls admin for this 
        try (Scanner sc = new Scanner(System.in)) {
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
    }

    protected void closeBranch(String Branch){

        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Enter branch name: ");
            String branchName = sc.nextLine(); 

            System.out.println("Enter branch location: ");  
            String branchLocation = sc.next();


            BranchDirectory branchDirectory = new BranchDirectory(new ArrayList<>()); 
            StaffDirectory staffDirectory = new StaffDirectory(new ArrayList<>());

            Branch branch = branchDirectory.getBranchByName(branchName);

            if(branch != null && branch.getBranchName().equals(branchName)){
                if(authoriseAdmin(StaffRoles.ADMIN)){
                    branchDirectory.rmvBranch(branchName, StaffRoles.ADMIN); 

                    ArrayList<Staff> staffToRemove = staffDirectory.filterBranch(branchName); 
                    for(Staff staff : staffToRemove){
                        staffDirectory.rmvStaff(staff.getStaffID(), StaffRoles.ADMIN);
                    }
                    System.out.println("Branch " + branchName + " at " + branchLocation + " has been closed.");
                }
                else{
                    System.out.println("You are not authorised to perform this action");
                }
            }
            else{
                System.out.println("Branch " + branchName + " at location " + branchLocation + " does not exist");
            }
        }
    }
    
    protected void assignManager(String staffId, String Branch){
        //int numManagers = getNumManagers(branch); 
        StaffDirectory staffDirectory = new StaffDirectory(new ArrayList<>());
 
        if(authoriseAdmin(StaffRoles.ADMIN)){
            int numManagers = staffDirectory.getNumManagers(StaffRoles.ADMIN);  
            int numManagersInBranch = 0; 
            ArrayList<Staff> allStaff = staffDirectory.getStaffDirectory(); 
            int numStaff = allStaff.size() - numManagers; 

            for(Staff staff : allStaff){
                if(staff.getRole() == StaffRoles.MANAGER){
                    numManagersInBranch++; 
                }
            }

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

            if(numManagersInBranch < quota){
                System.out.println("Manager is assigned to the Branch: " + Branch);
            }
            else{
                System.out.println("Cannot assign any more Managers. Quota reached for Branch. \n");
            }
        }
        else{
            System.out.println("You are not authorised to perform this action");
        }
    } 

    protected void promoteStaff(String staffID, String Branch){
        StaffDirectory staffDirectory = new StaffDirectory(new ArrayList<>()); 
        Staff staff = staffDirectory.getStaff(staffID);

        if(staff != null){
            StaffRoles currentRole = staff.getRole(); 

            switch(currentRole){
                case STAFF: 
                    staffRolesMap.put(staffID, StaffRoles.MANAGER);
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

    protected void transferStaff(String staffID, String Branch){
        StaffDirectory staffDirectory = new StaffDirectory(new ArrayList<>()); 
        Staff staff = staffDirectory.getStaff(staffID);
        BranchDirectory branchDirectory = new BranchDirectory(new ArrayList<>()); 
        
        if(staff != null){
            boolean newBranchExists = false; 
            for(Branch branch : branchDirectory.getBranchDirectory()){
                if(branch.getBranchName().equals(Branch)){
                    newBranchExists = true; 
                    branch.setBranchName(Branch); 
                    System.out.println("Staff member with ID " + staffID + " has been transferred to " + Branch + " successfully");
                    break; 
                }
            }
            if(!newBranchExists){
                System.out.println("This branch does not exist");
            }
        }
        else{
            System.out.println("Staff member with ID " + staffID + " not found");
        }
    }

    protected String addPayment(String method){
        Payment payment; 
        PaymentDirectory paymentDirectory = new PaymentDirectory(new ArrayList<>());
        switch(method){
            case "Credit/Debit Card":
                payment = new Payment(method); 
                paymentDirectory.addPaymentMtd(payment, StaffRoles.ADMIN); //since only admins can add payment methods 
                break; 
            case "Paypal":
                payment = new Payment(method); 
                paymentDirectory.addPaymentMtd(payment, StaffRoles.ADMIN); 
                break; 
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + method);
        } 
        return "Payment method added successfully"; 
    } 

    protected String removePayment(String method){ 
        PaymentDirectory paymentDirectory = new PaymentDirectory(new ArrayList<>()); 
        if (paymentDirectory.getPaymentDirectory().isEmpty()) {
            return "No payment methods available to remove";
        }
        
        Payment payment = paymentDirectory.getPaymentMtd(method); 
        if(payment != null){
            paymentDirectory.rmvPaymentMtd(method, StaffRoles.ADMIN);
            return "Payment method has been removed successfully"; 
        }
        return "Payment method not found.";
    }
}
