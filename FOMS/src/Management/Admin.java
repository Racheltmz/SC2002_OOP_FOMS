package Management;

import java.util.Scanner;

import Payment.Payment;

//import javax.smartcardio.CommandAPDU;

//import javax.swing.text.html.HTMLDocument.Iterator;
import java.util.Iterator; 

import java.util.ArrayList; 
//import java.util.List; 

public class Admin extends Staff{
    // List<Staff> staffList;
    // private List<String> branches; 
    // private List<String> paymentMethods; 
    //private int staffQuota; 
    private Company company; //admin class now has access to company class 

    public Admin(String staffID, String name, Roles role, char gender, int age, String branch){
        super(staffID, name, role, gender, age, branch); 
        //this.company = company; 
        // staffList = new ArrayList<>();
        // this.branches = new ArrayList<>();
        // this.paymentMethods = new ArrayList<>(); 

        // staffList.add(new Staff("KumarB", "Kumar Blackmore", Roles.STAFF, 'M', 32, "NTU"));
        // staffList.add(new Staff("Alexei", "Alexei", Roles.MANAGER, 'M', 25, "NTU"));
        // staffList.add(new Staff("TomC", "Tom Chan", Roles.MANAGER, 'M', 56, "JP"));
        // staffList.add(new Staff("AliciaA", "Alicia Ang", Roles.MANAGER, 'F', 27, "JE"));
        // staffList.add(new Staff("MaryL", "Mary Lee", Roles.STAFF, 'F', 44, "JE"));
        // staffList.add(new Staff("JustinL", "Justin Loh", Roles.STAFF, 'M', 49, "JP"));
        // staffList.add(new Staff("boss", "Boss", Roles.ADMIN, 'F', 62, "NTU"));
    }

    public void setRole(String staffID, String branch){
        Staff staff = company.getStaff(staffID);
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

    public void setBranch(String staffID, String branch){
        Staff staff = company.getStaff(staffID);
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

    public void setStaffID(String staffID, String newStaffID){
        Staff staff = company.getStaff(staffID);
        if(staff != null){
            staff.setStaffID(newStaffID);
            System.out.println("Staff ID updated successfully");
        }
        else{
            System.out.println("Staff member not found");
        }
    }

    protected void addBranch(String Branch){ //move all the branch methods to a different interface 
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

            System.out.println("Enter branch location: "); //should i add or just use branch name cause not much value added? 
            String branchLocation = sc.next();

            Company company = new Company(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()); 
            ArrayList<Branch> branches = company.getBranches(); 

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
        }
        //fire staff? 
    }

    // protected void displayStaffList(String filter){
    //     switch(filter.toLowerCase()){

    //         case "role":
    //             System.out.println("Staff List filtered by role (S, M, A): \n");
    //             for(Staff staff : staffList){
    //                 System.out.println(staff.getName() + " - Role: " + staff.getRole());
    //             } 
    //             break;
    //         case "gender":
    //             System.out.println("Staff List filtered by gender: \n");
    //             for(Staff staff : staffList){
    //                 System.out.println(staff.getName() + " - Gender: " + staff.getGender());
    //             }
    //             break; 
    //         case "age":
    //             System.out.println("Staff List filtered by age: \n");
    //             for(Staff staff : staffList){
    //                 System.out.println(staff.getName() + " - Age : " + staff.getAge());
    //             }
    //         case "branch":
    //         default:
    //             System.out.println("Staff List filtered by branch: \n");
    //             for(Staff staff : staffList){
    //                 System.out.println(staff.getName() + " - Branch: " + staff.getBranch());
    //             }
    //             break; 
    //     }
    // }

    protected void assignManager(String staffId, String Branch){
        //int numManagers = getNumManagers(branch); 
        Company company = new Company(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        
        int numManagers = company.getNumManagers(Roles.ADMIN); 

        int numStaff = company.getStaffList("Branch","staffID", Roles.MANAGER).size(); 

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
            System.out.println("Manager is assigned to the Branch: " + Branch);
        }
        else{
            System.out.println("Cannot assign any more Managers. Quota reached for Branch. \n");
        }
    }

    protected void promoteStaff(String staffID, String Branch){
        Staff staff = company.getStaff(staffID); 

        if(staff != null){
            Roles currentRole = staff.getRole(); 

            switch(currentRole){
                case STAFF: 
                    staff.setRole(Roles.MANAGER);
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
        Staff staff = company.getStaff(staffID);

        if(staff != null){
            //String currentBranch = staff.getBranch(); 

            boolean newBranchExists = false; 
            for(Branch branch : company.getBranches()){
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

    protected String addPayment(String method){
        Payment payment; 
        switch(method){
            case "Credit/Debit Card":
                payment = new Payment(method); 
                company.addPaymentMtd(payment, Roles.ADMIN); //since only admins can add payment methods 
                break; 
            case "Paypal":
                payment = new Payment(method); 
                company.addPaymentMtd(payment, Roles.ADMIN); 
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


