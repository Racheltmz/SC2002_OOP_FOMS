package utils;

import branch.Branch;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static utils.FileIOHelper.getSheet;

public class BranchXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Branch XLSX File in the data folder. Defaults to branch_list.xlsx.
     */
    private String branchXlsx;

    /**
     * Default Constructor to initialize this class with staff.xlsx as the XLSX file.
     */
    public BranchXlsxHelper() {
        this.branchXlsx = "branch_list.xlsx";
    }

    /**
     * Singleton instance of this class.
     */
    private static BranchXlsxHelper mInstance;

    /**
     * Gets the singleton instance of BranchXlsxHelper that reads from branch_list.xlsx
     *
     * @return Instance of this class
     */

    public static BranchXlsxHelper getInstance() {
        if (mInstance == null) mInstance = new BranchXlsxHelper();
        return mInstance;
    }

     /**
     * On initialisation, reads the XLSX file and parses it into an array list of branch objects.
     *
     * @return ArrayList of branch Objects.
     */
    public ArrayList<Branch> readFromXlsx() {
        XSSFSheet sheet = getSheet(this.branchXlsx);
        List<String[]> XlsxData = deserializeRecords(sheet, 1);
        ArrayList<Branch> branches = new ArrayList<>();
        if (XlsxData.isEmpty()) return branches;
        XlsxData.forEach((data) -> {
            if (data.length == 4) {
                UUID id = UUID.fromString(data[0]);
                // Convert to respective data type
                String name = data[1];
                String location = data[2];
                int staffQuota = (int) Double.parseDouble(data[3]);

                // Add new branch
                branches.add(new Branch(id, name, location, staffQuota));
            }
        });
        return branches;
    }

    /**
     * Writes a branch record to the XLSX File.
     *
     * @param branch Branch record to add
     * @param numExistingRecords Number of existing branch records
     * @throws IOException Error if there is an issue with IO processes
     */
    public void writeToXlsx(Branch branch, int numExistingRecords) throws IOException {
        String[] header = {"id", "name", "location", "manager"};
        serializeRecord(this.branchXlsx, branch.toXlsx(), header, numExistingRecords);
    }

    /**
     * Updates a branch record in the XLSX File.
     *
     * @param branch Branch record to add
     * @throws IOException Error if there is an issue with IO processes
     */
    public void updateXlsx(Branch branch) throws IOException {
        serializeUpdate(this.branchXlsx, branch.toXlsx(), branch.getId());
    }

    /**
     * Deletes a branch record in the XLSX File.
     *
     * @param id ID of branch record to delete
     * @throws IOException Error if there is an issue with IO processes
     */
    public void removeXlsx(UUID id) throws IOException {
        deleteRecord(this.branchXlsx, id);
    }
}
