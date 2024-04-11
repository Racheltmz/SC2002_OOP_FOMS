package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
public class BaseXlsxHelper {
    private final String dataDir = "./data/";

    public String getDataDir() {
        return dataDir;
    }

    // Get Excel spreadsheet by filename
    protected XSSFSheet getSheet(String filePath) {
        XSSFSheet sheet = null;
        try {
            // Reading file from local directory
            FileInputStream file = new FileInputStream(filePath);

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first sheet from the workbook
            sheet = workbook.getSheetAt(0);

            // Closing workbook and file output streams
            workbook.close();
            file.close();
        } catch (IOException ioException) {
            System.out.println("Error reading the Excel file: " + ioException.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sheet;
    }

    /**
     * Reads data from an Excel (XLSX) file and returns a List of String arrays representing rows and columns.
     *
     * @param sheet The Sheet object representing the Excel sheet to read from.
     * @param skip  How many rows in the file to skip (set 1 for header)
     * @return A list of string arrays containing all the Excel values
     */
    protected List<String[]> readAll(Sheet sheet, int skip) {
        List<String[]> result = new ArrayList<>();
        for (int i = skip; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                List<String> rowData = new ArrayList<>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        rowData.add(cell.toString());
                    } else {
                        rowData.add(""); // Empty cell
                    }
                }
                result.add(rowData.toArray(new String[0]));
            }
        }
        return result;
    }

    /**
     * Writes data to an Excel (XLSX) file.
     *
     * @param writeStrings List of String arrays representing rows and columns to write to the Excel file.
     * @param workbook     The Workbook object instance representing the Excel workbook.
     * @param sheetName    The name of the sheet to write data into.
     * @throws IOException If an I/O error occurs while writing to the Excel file.
     */
    protected void writeToXlsxFile(List<String[]> writeStrings, Workbook workbook, String sheetName) throws IOException {
        Sheet sheet = workbook.createSheet(sheetName);
        for (int i = 0; i < writeStrings.size(); i++) {
            Row row = sheet.createRow(i);
            String[] rowData = writeStrings.get(i);
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData[j]);
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
            workbook.write(fileOut);
        }
    }

    // TODO: NEW
    protected void writeHeader(String[] header, Row row) {
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    protected void writeRow(String[] data, Row row) {
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            Object obj = data[i];
            if (obj instanceof String)
                cell.setCellValue((String) obj);
            else if (obj instanceof Integer)
                cell.setCellValue((Integer) obj);
            else if (obj instanceof Double)
                cell.setCellValue((Double) obj);
        }
    }
}
