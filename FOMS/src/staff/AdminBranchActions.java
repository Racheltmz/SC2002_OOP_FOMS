package staff;

import static utils.ValidateHelper.validateIntRange;

import java.util.InputMismatchException;

import branch.Branch;
import branch.BranchDirectory;
import utils.InputScanner;

public class AdminBranchActions {
    private InputScanner sc = InputScanner.getInstance();
    private BranchDirectory branchDirectory = BranchDirectory.getInstance(); 

    protected void addBranch(StaffRoles auth) {
        try {
            // Get branch name
            System.out.print("Enter branch name: ");
            String name = sc.nextLine();
            // Get location
            System.out.print("Enter branch location: ");
            String location = sc.nextLine();
            // Get staff quota
            int quota = validateIntRange("Enter staff quota: ", 1, 15);
            Branch newBranch = new Branch(name, location, quota);
            branchDirectory.addBranch(newBranch, auth);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    protected void closeBranch(StaffRoles auth) {
        // Get branch by name
        Branch branchToRmv = branchDirectory.getBranchByUserInput();
        branchDirectory.rmvBranch(branchToRmv, auth);
    }
}
