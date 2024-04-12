package utils;

import branch.Branch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BranchXlsxHelper extends BaseXlsxHelper {
    /**
     * Path to Branch XLSX File in the data folder. Defaults to branch_list.xlsx.
     */
    private final String branchXlsx = "branch_list.xlsx";
    private final String[] header = {"id", "name", "location", "manager"};

    /**
     * Default Constructor.
     */
    public BranchXlsxHelper() {}

    /**
     * Singleton instance of BranchXlsxHelper.
     */
    private static BranchXlsxHelper branchInstance;

    /**
     * Gets the singleton instance of BranchXlsxHelper that reads from branch_list.xlsx
     *
     * @return Instance of this class
     */

    public static BranchXlsxHelper getInstance() {
        if (branchInstance == null) branchInstance = new BranchXlsxHelper();
        return branchInstance;
    }

     /**
     * On initialisation, reads the XLSX file and parses it into an ArrayList of Branch objects.
     *
     * @return ArrayList of Branch Objects.
     */
    public ArrayList<Branch> readFromXlsx() {
        // Initialise a list
        ArrayList<Branch> branches = new ArrayList<>();

        // Deserialize records
        List<String[]> XlsxData = deserializeRecords(this.branchXlsx, this.header, 4, 1);
        if (XlsxData.isEmpty()) {
            serializeHeader(this.branchXlsx, this.header);
            return branches;
        }

        // Add branches
        XlsxData.forEach((data) -> {
            UUID id = UUID.fromString(data[0]);
            // Convert to respective data type
            String name = data[1];
            String location = data[2];
            int staffQuota = (int) Double.parseDouble(data[3]);

            branches.add(new Branch(id, name, location, staffQuota));
        });
        return branches;
    }

    /**
     * Writes a Branch record to the XLSX File.
     *
     * @param branch Branch record to add
     * @param numExistingRecords Number of existing branch records
     */
    public void writeToXlsx(Branch branch, int numExistingRecords) {
        serializeRecord(this.branchXlsx, branch.toXlsx(), numExistingRecords);
    }

    /**
     * Updates a Branch record in the XLSX File.
     *
     * @param branch Branch record to add
     */
    public void updateXlsx(Branch branch) {
        serializeUpdate(this.branchXlsx, branch.toXlsx(), branch.getId());
    }

    /**
     * Deletes a Branch record in the XLSX File.
     *
     * @param id ID of Branch record to delete
     */
    public void removeXlsx(UUID id) {
        deleteRecord(this.branchXlsx, id);
    }
}
