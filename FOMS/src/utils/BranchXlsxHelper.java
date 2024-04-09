package utils;

import branch.Branch;
import exceptions.IllegalArgumentException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BranchXlsxHelper extends BaseXlsxHelper {

    /**
     * Path to Branch XLSX File in the data folder. Defaults to menu.xlsx.
     */
    private String branchXlsx = "branch_list.xlsx";
    /**
     * Singleton instance of this class.
     */
    private static BranchXlsxHelper mInstance;

    /**
     * Default Constructor to initialize this class with branch_list.xlsx as the XLSX file.
     */
    private BranchXlsxHelper() {
    }
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
     * @throws IOException Unable to read from file.
     */
    public ArrayList<Branch> readFromXlsx() throws IOException {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(FileIOHelper.getFileInputStream(this.branchXlsx));
            Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet is where data is stored
            List<String[]> xlsxData = readAll(sheet, 1);
            ArrayList<Branch> branches = new ArrayList<>();
            if (xlsxData.size() == 0) return branches;
            xlsxData.forEach((str) -> {
                try {
                    branches.add(new Branch(str));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            return branches;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    public void writeToXlsx(ArrayList<Branch> branches) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Branches");
        String[] header = {"Name", "Location", "Manager"};
        writeHeader(header, sheet.createRow(0));
        for (int i = 0; i < branches.size(); i++) {
            writeRow(branches.get(i).toXlsx(), sheet.createRow(i + 1));
        }
        try (FileOutputStream fileOut = new FileOutputStream(this.branchXlsx)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    private void writeHeader(String[] header, Row row) {
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    private void writeRow(String[] data, Row row) {
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data[i]);
        }
    }
}
