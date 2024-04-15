package staff;

// import branch.Branch;
import branch.BranchDirectory;
import staff.filter.StaffFilterAge;
import staff.filter.StaffFilterBranch;
import staff.filter.StaffFilterGender;
import staff.filter.StaffFilterOptions;
import staff.filter.StaffFilterRole;
import utils.IFilter;
import utils.InputScanner;

public class StaffFilterActions {
    private InputScanner sc = InputScanner.getInstance(); 
    private BranchDirectory branchDirectory = BranchDirectory.getInstance();

    protected void filterStaff(StaffFilterOptions option) { 
        switch (option) {
            case BRANCH:
                String branch = branchDirectory.getBranchByUserInput().getName();
                filterByBranch(branch);
                break;
            case ROLE:
                String roleChoice; 
                do{
                    System.out.print("Select role (S/M):\nS: Staff\nM: Manager");
                    roleChoice = sc.next().toUpperCase(); 
                    if(!roleChoice.equals("S") && !roleChoice.equals("M")){
                        System.out.print("Invalid role. Please enter 'S' for staff and 'M' for manager"); 
                    }
                }while(!roleChoice.equals("S") && !roleChoice.equals("M")); 
                filterByRole(roleChoice);
                break;
            case GENDER:
                String genderChoice; 
                do{
                    System.out.print("Select gender (M/F):");
                    genderChoice = sc.next().toUpperCase(); 
                    if(!genderChoice.equals("M") && !genderChoice.equals("F")){
                        System.out.print("Invalid input. Please enter 'M' for male and 'F' for female"); 
                    }
                }while(!genderChoice.equals("M") && !genderChoice.equals("F")); 
                filterByGender(genderChoice);
                break;
            case AGE:
                int ageChoice = 0;
                boolean validAge = false; 
                do{
                    try{
                        System.out.print("Enter age:");
                        ageChoice = sc.nextInt(); 
                        if(ageChoice >= 16 && ageChoice <= 80){
                            validAge = true; 
                        }
                        else{
                            System.out.print("Age must be between 16 and 80"); 
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.print("Invalid Input. Please enter a valid age."); 
                    }
                }while(!validAge); 
                filterByAge(ageChoice);
                break;
        }
    }

    private void filterByBranch(String branch) {
        IFilter staffFilterBranch = new StaffFilterBranch();
        staffFilterBranch.filter(branch);
    }

    private void filterByRole(String role) {
        IFilter staffFilterRole = new StaffFilterRole();
        staffFilterRole.filter(role);
    }

    private void filterByGender(String gender) {
        IFilter staffFilterGender = new StaffFilterGender();
        staffFilterGender.filter(gender);
    }

    private void filterByAge(int age) {
        if(age < 16 || age > 80){
            System.out.print("Your age must be between 16 and 80");
            return; 
        }
        IFilter staffFilterAge = new StaffFilterAge();
        staffFilterAge.filter(String.valueOf(age));
    }

    // Public method to expose functionality in a controlled way
    public void applyRoleFilter(String role) {
        filterByRole(role);
    }

    public void applyBranchFilter(String branch) {
        filterByBranch(branch);
    }

    public void applyGenderFilter(String gender){
        filterByGender(gender);
    }

    public void applyAgeFilter(int age){
        filterByAge(age);
    }
}
