package branch;

import staff.StaffRoles;

import java.util.ArrayList;
import java.util.Objects;

import static authorisation.Authorisation.authoriseAdmin;
import static validation.ValidateDataType.validateInt;

// Records of branch
public class BranchDirectory {
    // Attributes
    private ArrayList<Branch> branchDirectory;

    // Constructor
    public BranchDirectory(ArrayList<Branch> branchDirectory) {
        this.branchDirectory = branchDirectory;
    }

    // Functionalities
    // Get all branches
    public ArrayList<Branch> getBranchDirectory() {
        return branchDirectory;
    }

    // Get branch based on branch name
    public Branch getBranchByName(String name) {
        // Return staff object if it can be found
        for (Branch branch : this.branchDirectory) {
            if (Objects.equals(branch.getBranchName(), name))
                return branch;
        }
        // Return null if branch cannot be found
        return null;
    }

    // Display branches
    public void displayBranches() {
        System.out.println("Branches: ");
        for (int i = 0; i < this.branchDirectory.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.branchDirectory.get(i).getBranchName());
        }
    }

    // Get branch name by user selection
    public static String getBranchByUserInput(BranchDirectory branchDirectory) {
        // Display branches
        branchDirectory.displayBranches();
        // Get branches
        ArrayList<Branch> branches = branchDirectory.getBranchDirectory();
        // Handle invalid branch names by checking if index out of bounds
        String branchName = null;
        boolean success = false;
        do {
            try {
                // Get user's selection
                int branchIndex = validateInt("Select Branch: ");
                branchName = branches.get(branchIndex - 1).getBranchName();
                success = true;
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Invalid value, please enter again.");
            }
        } while (!success);
        return branchName;
    }

    // Add branch (admin purposes)
    public void addBranch(Branch branch, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.branchDirectory.add(branch);
    }

    // Remove branch (admin purposes)
    public void rmvBranch(String branchName, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.branchDirectory.remove(this.getBranchByName(branchName));
    }
}