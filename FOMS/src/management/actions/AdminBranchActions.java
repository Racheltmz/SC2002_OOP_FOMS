package management.actions;

import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.validateIntRange;
import static utils.ValidateHelper.validateString;

import java.util.InputMismatchException;

import branch.Branch;
import branch.BranchDirectory;
import utils.InputScanner;

public class AdminBranchActions implements IAdminBranchActions {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();

    public void addBranch() {
        try {
            // Get branch name
            String name = validateString("Enter branch name: ");

            // Get location
            String location = validateString("Enter branch location: ");

            // Get staff quota
            int quota = validateIntRange("Enter staff quota: ", 1, 15);
            Branch newBranch = new Branch(name.toUpperCase(), toProperCase(location), quota);
            branchDirectory.addBranch(newBranch);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void closeBranch() {
        // Get branch by name
        Branch branchToRmv = branchDirectory.getBranchByUserInput();
        branchDirectory.rmvBranch(branchToRmv);
    }
}