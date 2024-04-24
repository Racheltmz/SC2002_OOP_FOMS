package management.actions;

import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.validateIntRange;
import static utils.ValidateHelper.validateString;

import java.util.InputMismatchException;

import branch.Branch;
import branch.BranchDirectory;

/**
 * AdminBranchActions class provides methods for performing administrative actions related to branches,
 * such as adding a new branch and closing an existing branch.
 */
public class AdminBranchActions implements IAdminBranchActions {
    BranchDirectory branchDirectory = BranchDirectory.getInstance();

    /**
     * Adds a new branch to the system.
     * Prompts the user to input the branch name, location, and staff quota.
     * Validates the inputs and adds a new branch to the branchDirectory.
     */
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

    /**
     * Closes an existing branch in the system.
     * Prompts the user to select the branch to be closed, and
     * removes the selected branch from the branch directory.
     */
    public void closeBranch() {
        // Get branch by name
        Branch branchToRmv = branchDirectory.getBranchByUserInput();
        branchDirectory.rmvBranch(branchToRmv);
    }
}