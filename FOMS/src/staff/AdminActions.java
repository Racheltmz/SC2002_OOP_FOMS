package staff;

// import static staff.AdminView.displayAllStaff;
// import static utils.ValidateHelper.validateInt;
// import static utils.ValidateHelper.validateIntRange;

// import java.util.InputMismatchException;
// import java.util.Scanner;        

import branch.Branch;
// import branch.BranchDirectory;
// import exceptions.DuplicateEntryException;
import staff.filter.*;
// import utils.IFilter;
// import utils.InputScanner;

// Class containing actions that admin can perform
// TODO: AFREEN, improve the ui
// TODO: AFREEN, SRP create more files & split functions based on functionalities (staff account, staff role, branch, payment) (done)
public class AdminActions implements IAdminActions{
    private StaffFilterActions staffFilterActions; 
    private AdminBranchActions adminBranchActions; 
    private StaffAccountActions staffAccountActions; 
    private StaffRoleChanges staffRoleChanges; 

    public AdminActions(StaffFilterActions staffFilterActions, AdminBranchActions adminBranchActions, StaffAccountActions staffAccountActions, StaffRoleChanges staffRoleChanges){
        this.staffFilterActions = staffFilterActions; 
        this.adminBranchActions = adminBranchActions; 
        this.staffAccountActions = staffAccountActions; 
        this.staffRoleChanges = staffRoleChanges; 
    }

    // Add staff
    @Override 
    public void addStaff(StaffRoles auth) {
        staffAccountActions.addStaff(auth); 
        // try {
        //     StaffDirectory staffDirectory = StaffDirectory.getInstance();
        //     int numExistingStaff = staffDirectory.getNumStaff();
        //     // Get details of staff
        //     System.out.print("Enter name: ");
        //     String name = sc.next();

        //     String staffId = null;
        //     // Keep getting user input until they enter a valid staff id
        //     do {
        //         try {
        //             System.out.print("Enter staff ID: ");
        //             staffId = sc.next();
        //             boolean isExisting = staffDirectory.staffExistsByStaffID(staffId);
        //             if (isExisting) {
        //                 staffId = null;
        //                 throw new DuplicateEntryException("Staff not inserted: Duplicate staff entered.");
        //             }
        //         } catch (DuplicateEntryException e) {
        //             System.out.println(e.getMessage());
        //         }
        //     } while (staffId == null);

        //     // Validating the gender 
        //     char gender; 

        //     do{
        //         System.out.print("Enter gender (M/F): ");
        //         gender = sc.next().toUpperCase().charAt(0); 
        //         if(gender != 'M' && gender != 'F'){
        //             System.out.print("Invalid input. Please enter 'M or 'F");
        //         }
        //     }while(gender != 'M' && gender != 'F');

        //     // TODO: AFREEN validate if its M or F (done)
            
        //     int age = validateInt("Enter age: ");
        //     System.out.print("Select branch: ");
        //     Branch branch = branchDirectory.getBranchByUserInput();

        //     // Add new staff
        //     staffDirectory.addStaff(new Staff(staffId, name, StaffRoles.STAFF, gender, age, branch), numExistingStaff, auth);
        // } catch (InputMismatchException e) {
        //     System.out.println(e.getMessage());
        // }
    }

    // Update staff
    public void updateStaff(StaffRoles auth) {
        staffAccountActions.updateStaff(auth); 
    //     StaffDirectory staffDirectory = StaffDirectory.getInstance();
    //     displayAllStaff(staffDirectory.getStaffDirectory(), auth);
    //     Staff staffToUpdate = staffDirectory.getStaff();
    //     boolean success = false;
    //     do {
    //         try {
    //             // Update details
    //             int age = validateInt("Enter age: ");
    //             staffToUpdate.setAge(age);
    //             staffDirectory.updateStaff(staffToUpdate);
    //             success = true;
    //         } catch (IndexOutOfBoundsException e) {
    //             System.out.print("Invalid value, please enter again.");
    //         } catch (InputMismatchException e) {
    //             System.out.print("Error: " + e.getMessage());
    //         }
    //     } while (!success);
    }

    // Remove staff
    public void removeStaff(StaffRoles auth) {
        staffAccountActions.removeStaff(auth); 
        // StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // displayAllStaff(staffDirectory.getStaffDirectory(), auth);
        // Staff staffToRmv = staffDirectory.getStaff();
        // staffDirectory.rmvStaff(staffToRmv, auth);
    }

    // TODO: confirm design of filter
    // TODO: AFREEN, VALIDATE USER INPUT (WHETHER ITS ACTL M/F ETC) (done)
    public void filterStaff(StaffFilterOptions option) { 
        switch(option){
            case BRANCH:
                staffFilterActions.applyBranchFilter(null);
                break;  
            case ROLE:
                staffFilterActions.applyRoleFilter(StaffRoles.STAFF.getAcronym());
                break; 
            case GENDER:
                staffFilterActions.applyGenderFilter(null);
                break; 
            case AGE:
                staffFilterActions.applyAgeFilter(0);
                break;
        }
        // switch (option) {
        //     case BRANCH:
        //         String branch = branchDirectory.getBranchByUserInput().getName();
        //         filterByBranch(branch);
        //         break;
        //     case ROLE:
        //         String roleChoice; 
        //         do{
        //             System.out.print("Select role (S/M):\nS: Staff\nM: Manager");
        //             roleChoice = sc.next().toUpperCase(); 
        //             if(!roleChoice.equals("S") && !roleChoice.equals("M")){
        //                 System.out.print("Invalid role. Please enter 'S' for staff and 'M' for manager"); 
        //             }
        //         }while(!roleChoice.equals("S") && !roleChoice.equals("M")); 
        //         filterByRole(roleChoice);
        //         break;
        //     case GENDER:
        //         String genderChoice; 
        //         do{
        //             System.out.print("Select gender (M/F):");
        //             genderChoice = sc.next().toUpperCase(); 
        //             if(!genderChoice.equals("M") && !genderChoice.equals("F")){
        //                 System.out.print("Invalid input. Please enter 'M' for male and 'F' for female"); 
        //             }
        //         }while(!genderChoice.equals("M") && !genderChoice.equals("F")); 
        //         filterByGender(genderChoice);
        //         break;
        //     case AGE:
        //         int ageChoice = 0;
        //         boolean validAge = false; 
        //         do{
        //             try{
        //                 System.out.print("Enter age:");
        //                 ageChoice = sc.nextInt(); 
        //                 if(ageChoice >= 16 && ageChoice <= 80){
        //                     validAge = true; 
        //                 }
        //                 else{
        //                     System.out.print("Age must be between 16 and 80"); 
        //                 }
        //             }
        //             catch(NumberFormatException e){
        //                 System.out.print("Invalid Input. Please enter a valid age."); 
        //             }
        //         }while(!validAge); 
        //         filterByAge(ageChoice);
        //         break;
        // }
    }

    // private void filterByBranch(String branch) {
    //     IFilter staffFilterBranch = new StaffFilterBranch();
    //     staffFilterBranch.filter(branch);
    // }

    // private void filterByRole(String role) {
    //     IFilter staffFilterRole = new StaffFilterRole();
    //     staffFilterRole.filter(role);
    // }

    // private void filterByGender(String gender) {
    //     IFilter staffFilterGender = new StaffFilterGender();
    //     staffFilterGender.filter(gender);
    // }

    // // TODO: AFREEN, we should filter by less than/equal/more than a certain age (more informative) (done)
    // private void filterByAge(int age) {
    //     if(age < 16 || age > 80){
    //         System.out.print("Your age must be between 16 and 80");
    //         return; 
    //     }
    //     IFilter staffFilterAge = new StaffFilterAge();
    //     staffFilterAge.filter(String.valueOf(age));
    // }

    public boolean assignManager(Branch branch, StaffRoles auth) {
        return staffAccountActions.assignManager(branch, auth); 
        // StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // // Get number of managers
        // int numManagers = staffDirectory.getNumManagersByBranch(branch, auth);
        // // Check if the manager quota has exceeded
        // if (numManagers < branch.getManagerQuota()) {
        //     System.out.print("Able to assign staff as a manager. The manager quota for " + branch.getLocation() + " has not been met.");
        //     return true;
        // } else {
        //     System.out.print("Unable to assign staff as a manager. The manager quota for " + branch.getLocation() + " is already met.");
        //     return false;
        // }
    }

    public void promoteStaff(StaffRoles auth){
        staffRoleChanges.promoteStaff(auth); 
        // StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // filterByRole(StaffRoles.STAFF.getAcronym());
        // Staff staff = staffDirectory.getStaff();
        // StaffRoles currentRole = staff.getRole();
        // switch (currentRole) {
        //     case STAFF:
        //         if (assignManager(staff.getBranch(), auth)) {
        //             staffDirectory.upgradeCredentials(staff, auth);
        //             System.out.print("Staff member with ID " + staff.getStaffID() + " has been promoted to Manager.");
        //         }
        //         break;
        //     case MANAGER:
        //         System.out.print("Manager cannot be promoted.");
        //         break;
        //     case ADMIN:
        //         System.out.println("Admin cannot be promoted.");
        //         break;
        // }
    }

    // TODO: Afreen, ensure they don't transfer to the branch they were originally in(done)
    public void transferStaff(StaffRoles auth){
        staffAccountActions.transferStaff(auth); 
        // Scanner sc = new Scanner(System.in); 
        // StaffDirectory staffDirectory = StaffDirectory.getInstance();
        // displayAllStaff(staffDirectory.getStaffDirectory(), auth);
        // Staff staff = staffDirectory.getStaff();
        // Branch branchToTransfer; 

        // // Repeatedly ask for a valid branch until one is chosen 
        // do{
        //     System.out.print("Select a branch to transfer to" );
        //     branchToTransfer = branchDirectory.getBranchByUserInput(); 
        //     if(staff.getBranch().equals(branchToTransfer)){
        //         System.out.print("Staff member was already in this branch. Choose a different branch.");
        //     }
        // }while(staff.getBranch().equals(branchToTransfer)); 

        // if (staff.getRole() == StaffRoles.STAFF ||
        //         (staff.getRole() == StaffRoles.MANAGER && assignManager(branchToTransfer, auth))) {
        //     staff.setBranch(branchToTransfer);
        //     staffDirectory.updateStaff(staff);
        //     System.out.print("Staff member with ID " + staff.getStaffID() + " has been transferred to " + branchToTransfer.getLocation() + " successfully");
        // }
    }

    public void addBranch(StaffRoles auth) {
        adminBranchActions.addBranch(auth); 
        // try {
        //     // Get branch name
        //     System.out.print("Enter branch name: ");
        //     String name = sc.nextLine();
        //     // Get location
        //     System.out.print("Enter branch location: ");
        //     String location = sc.nextLine();
        //     // Get staff quota
        //     int quota = validateIntRange("Enter staff quota: ", 1, 15);
        //     Branch newBranch = new Branch(name, location, quota);
        //     branchDirectory.addBranch(newBranch, auth);
        // } catch (InputMismatchException e) {
        //     System.out.println("Error: " + e.getMessage());
        // }
    }

    public void closeBranch(StaffRoles auth) {
        adminBranchActions.closeBranch(auth);
        // Get branch by name
        // Branch branchToRmv = branchDirectory.getBranchByUserInput();
        // branchDirectory.rmvBranch(branchToRmv, auth);
    }


//    protected String addPayment(String method){
//        PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();
//        Payment payment;
//
//        switch(method){
//            case "Credit/Debit Card":
//                payment = new Payment(method);
//                paymentDirectory.addPaymentMtd(payment, StaffRoles.ADMIN); //since only admins can add payment methods
//                break;
//            case "Paypal":
//                payment = new Payment(method);
//                paymentDirectory.addPaymentMtd(payment, StaffRoles.ADMIN);
//                break;
//            default:
//                throw new IllegalArgumentException("Unsupported payment method: " + method);
//        }
//        return "Payment method added successfully";
//    }
//
//    protected String removePayment(String method){
//        PaymentDirectory paymentDirectory = PaymentDirectory.getInstance();
//        if (paymentDirectory.getPaymentDirectory().isEmpty()) {
//            return "No payment methods available to remove";
//        }
//
//        Payment payment = paymentDirectory.getPaymentMtd(method);
//        if(payment != null){
//            paymentDirectory.rmvPaymentMtd(method, StaffRoles.ADMIN);
//            return "Payment method has been removed successfully";
//        }
//        return "Payment method not found.";
//    }


    // TEMP
    //    // Setter for updating role of staff member
//    public void setRole(String staffID, String branch){
//        String staffIDFromObject = this.getStaffID();
//        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
//            if(this.getBranch().equals(branch)){
//                System.out.println("Role updated successfully.");
//            }
//            else{
//                System.out.println("This staff member does not belong to this branch.");
//            }
//        }
//        else{
//            System.out.println("Staff member not found.");
//        }
//    }
//
//    // setter for updating branch of staff member
//    public void setBranch(String staffID, String branch){
//        String staffIDFromObject = this.getStaffID();
//        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
//            if(this.getBranch().equals(branch)){
//                System.out.println("Branch updated successfully");
//            }
//            else{
//                System.out.println("This staff member does not belong to this branch");
//            }
//        }
//        else{
//            System.out.println("Staff member not found");
//        }
//    }
//
//    // setter for updating staff ID
//    public void setStaffID(String staffID, String newStaffID){
//        String staffIDFromObject = this.getStaffID();
//        if(staffIDFromObject != null && staffIDFromObject.equals(staffID)){
//            try{
//                Field staffIDField = Staff.class.getDeclaredField("staffID");
//                staffIDField.setAccessible(true);
//                staffIDField.set(this, newStaffID);
//                System.out.println("Staff ID updated successfully");
//            }
//            catch(NoSuchFieldException | IllegalAccessException e){
//                System.out.println("Error updating staff ID: " + e.getMessage());
//            }
//        }
//        else{
//            System.out.println("Staff member not found");
//        }
//    }
}
