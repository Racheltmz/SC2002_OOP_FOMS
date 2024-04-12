package branch;

import staff.StaffRoles;
import utils.BranchXlsxHelper;

import java.util.ArrayList;
import java.util.Objects;

import static authorisation.Authorisation.authoriseAdmin;
import static utils.ValidateHelper.validateInt;

// Records of branch
public class BranchDirectory {
    // Attribute
    private final ArrayList<Branch> branchDirectory;
    private static BranchDirectory branchSingleton = null;

    private BranchDirectory() {
        BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
        this.branchDirectory = branchXlsxHelper.readFromXlsx();
    }

    public static BranchDirectory getInstance() {
        if (branchSingleton == null) {
            branchSingleton = new BranchDirectory();
        }
        return branchSingleton;
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
            if (Objects.equals(branch.getName(), name))
                return branch;
        }
        // Return null if branch cannot be found
        return null;
    }

    // Display branches
    public void displayBranches() {
        System.out.println("Branches: ");
        for (int i = 0; i < this.branchDirectory.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.branchDirectory.get(i).getLocation());
        }
    }

    // Get branch name by user selection
    public String getBranchByUserInput() {
        // Display branches
        displayBranches();
        // Handle invalid branch names by checking if index out of bounds
        String branchName = null;
        boolean success = false;
        do {
            try {
                // Get user's selection
                int branchIndex = validateInt("Select Branch: ");
                branchName = this.branchDirectory.get(branchIndex - 1).getName();
                success = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid value, please enter again.");
            }
        } while (!success);
        return branchName;
    }

    // Add branch (admin purposes)
    public void addBranch(Branch branch, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            this.branchDirectory.add(branch);
            BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
            branchXlsxHelper.writeToXlsx(branch, this.branchDirectory.size());
        }
    }

    // Remove branch (admin purposes)
    public void rmvBranch(String branchName, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.branchDirectory.remove(this.getBranchByName(branchName));
    }
}
