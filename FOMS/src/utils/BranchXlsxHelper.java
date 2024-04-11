package utils;

import branch.Branch;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import staff.Staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * Reads the XLSX file and parses it into an array list of branch objects.
     *
     * @return ArrayList of branch Objects.
     */
    public ArrayList<Branch> readFromXlsx() {
        XSSFSheet sheet = getSheet(this.branchXlsx);
        List<String[]> XlsxData = deserializeRecords(sheet, 1);
        ArrayList<Branch> branches = new ArrayList<>();
        if (XlsxData.isEmpty()) return branches;
        XlsxData.forEach((data) -> {
            if (data.length == 3) {
                // Convert to respective data type
                String name = data[0];
                String location = data[1];
                int staffQuota = (int) Double.parseDouble(data[2]);

                // Add new branch
                branches.add(new Branch(name, location, staffQuota));
            }
        });
        return branches;
    }

    public void writeToXlsx(Branch branch, int numExistingRecords) throws IOException {
        String[] header = {"id", "name", "location", "manager"};
        serializeRecord(this.branchXlsx, branch.toXlsx(), header, numExistingRecords);
    }

//    public void updateXlsx(Branch branch, int idxToUpdate) throws IOException {
//        serializeUpdate(this.branchXlsx, branch.toXlsx(), idxToUpdate);
//    }



//    public void writeToXlsx(ArrayList<Branch> branches) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Branches");
//        String[] header = {"Name", "Location", "Manager"};
//        writeHeader(header, sheet.createRow(0));
//        for (int i = 0; i < branches.size(); i++) {
//            writeRow(branches.get(i).toXlsx(), sheet.createRow(i + 1));
//        }
//        try (FileOutputStream fileOut = new FileOutputStream(this.branchXlsx)) {
//            workbook.write(fileOut);
//        } finally {
//            workbook.close();
//        }
//    }

//    private void writeHeader(String[] header, Row row) {
//        for (int i = 0; i < header.length; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(header[i]);
//        }
//    }
//
//    private void writeRow(String[] data, Row row) {
//        for (int i = 0; i < data.length; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(data[i]);
//        }
//    }

}
