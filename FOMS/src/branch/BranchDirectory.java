package branch;

import static authorisation.Authorisation.*;
import static utils.ValidateHelper.*;

import java.util.ArrayList;
import java.util.Objects;

import exceptions.ExcelFileNotFound;
import io.BranchXlsxHelper;
import staff.Staff;
import staff.StaffDirectory;
import staff.StaffRoles;

// Records of branch
public class BranchDirectory {
    // Attribute
    private ArrayList<Branch> branchDirectory;
    private static BranchDirectory branchSingleton = null;

    private BranchDirectory()  {
        BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
        this.branchDirectory = branchXlsxHelper.readFromXlsx();
    }

    public static BranchDirectory getInstance() throws ExcelFileNotFound {
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
        return null;
    }

    // TODO: can move to a view
    // Display branches
    public void displayBranches() {
        System.out.println("Branches: ");
        for (int i = 0; i < this.branchDirectory.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.branchDirectory.get(i).getLocation());
        }
    }

    // Get branch name by user selection
    public Branch getBranchByUserInput() {
        // Display branches
        displayBranches();
        // Handle invalid branch names by checking if index out of bounds
        Branch branch = null;
        boolean success = false;
        do {
            try {
                // Get user's selection
                int branchIndex = validateInt("Select Branch: ");
                branch = this.branchDirectory.get(branchIndex - 1);
                success = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid value, please enter again.");
            }
        } while (!success);
        return branch;
    }

    // Add branch
    public void addBranch(Branch branch, StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            int numExistingBranches = this.branchDirectory.size();
            this.branchDirectory.add(branch);
            BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
            branchXlsxHelper.writeToXlsx(branch, numExistingBranches);
            System.out.println("Branch successfully created!");
        }
    }

    // Remove branch
    public void rmvBranch(Branch branchToRmv, StaffRoles auth) throws ExcelFileNotFound {
        if (authoriseAdmin(auth)) {
            StaffDirectory staffDirectory = StaffDirectory.getInstance();
            // Remove staff under the branch
            ArrayList<Staff> staffToRmv = new ArrayList<>();
            for (Staff staff: staffDirectory.getStaffDirectory()) {
                if (staff.getRole() != StaffRoles.ADMIN && staff.getBranch().equals(branchToRmv)) {
                    staffToRmv.add(staff);
                }
            }
            staffDirectory.rmvStaffByBranch(staffToRmv, auth);
            // Remove branch
            this.branchDirectory.removeIf(branch -> branch.getName().equals(branchToRmv.getName()));
            BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
            branchXlsxHelper.removeXlsx(branchToRmv.getId());
            System.out.println("Branch successfully closed and staff records for this branch have been deleted.");
        }
    }

    public int getNumBranches() {
        return branchDirectory.size();
    }
}
