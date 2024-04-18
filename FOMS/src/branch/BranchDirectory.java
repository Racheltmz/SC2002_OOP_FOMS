package branch;

import menu.MenuDirectory;
import menu.MenuItem;
import management.Staff;
import management.StaffDirectory;
import management.StaffRoles;
import io.BranchXlsxHelper;

import java.util.ArrayList;
import java.util.Objects;

import static utils.ValidateHelper.validateInt;

/**
 * Represents an overall directory of branches.
 */
public class BranchDirectory {

    /** The arraylist of branches in this BranchDirectory. */
    private final ArrayList<Branch> branchDirectory;

    /** The singleton instance of this BranchDirectory. */
    private static BranchDirectory branchSingleton = null;

    /**
     * Constructs a BranchDirectory by reading data from the associated XLSX file, branch_list.xlsx.
     */
    private BranchDirectory() {
        BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
        this.branchDirectory = branchXlsxHelper.readFromXlsx();
    }

    /**
     * Gets the singleton instance of this BranchDirectory.
     * @return this BranchDirectory's singleton instance.
     */
    public static BranchDirectory getInstance() {
        if (branchSingleton == null) {
            branchSingleton = new BranchDirectory();
        }
        return branchSingleton;
    }

    /**
     * Gets the arraylist of branches in this BranchDirectory.
     *
     * @return This BranchDirectory's arraylist of branches.
     */
    public ArrayList<Branch> getBranchDirectory() {
        return branchDirectory;
    }

    /**
     * Gets a branch from this BranchDirectory based on Branch name.
     *
     * @param name The name of the branch.
     * @return The branch with the specified name, or null if not found.
     */
    public Branch getBranchByName(String name) {
        // Return staff object if it can be found
        for (Branch branch : this.branchDirectory) {
            if (Objects.equals(branch.getName(), name))
                return branch;
        }
        return null;
    }

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
    public void addBranch(Branch branch) {
        int numExistingBranches = this.branchDirectory.size();
        this.branchDirectory.add(branch);
        BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
        branchXlsxHelper.writeToXlsx(branch, numExistingBranches);
        System.out.println("Branch successfully created!");
    }

    public void updateBranch(Branch updatedBranch){
        Branch branch = getBranchByName(updatedBranch.getName());
        branch.setName(updatedBranch.getName());
        branch.setLocation(updatedBranch.getLocation());
        branch.setStaffQuota(updatedBranch.getStaffQuota());
    }

    // Remove branch
    public void rmvBranch(Branch branchToRmv) {
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        MenuDirectory menuDirectory = MenuDirectory.getInstance();

        // Remove staff under the branch
        ArrayList<Staff> staffToRmv = new ArrayList<>();
        for (Staff staff: staffDirectory.getStaffDirectory()) {
            if (staff.getRole() != StaffRoles.ADMIN && staff.getBranch().equals(branchToRmv)) {
                staffToRmv.add(staff);
            }
        }
        staffDirectory.rmvStaffByBranch(staffToRmv);

        // Remove menu items under the branch
        String branchName = branchToRmv.getName();
        ArrayList <MenuItem> menuItems = menuDirectory.getMenu(branchName).getMenuItems();
        menuDirectory.rmvMenuByBranch(menuItems, branchName);

        // Remove branch
        this.branchDirectory.removeIf(branch -> branch.getName().equals(branchToRmv.getName()));
        BranchXlsxHelper branchXlsxHelper = BranchXlsxHelper.getInstance();
        branchXlsxHelper.removeXlsx(branchToRmv.getId());
        System.out.println("Branch successfully closed and staff records for this branch have been deleted.");
    }

    public int getNumBranches() {
        return branchDirectory.size();
    }
}