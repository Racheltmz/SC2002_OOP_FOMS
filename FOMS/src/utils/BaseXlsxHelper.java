package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
public class BaseXlsxHelper {
    /**
     * Reads data from an Excel (XLSX) file and returns a List of String arrays representing rows and columns.
     *
     * @param sheet The Sheet object representing the Excel sheet to read from.
     * @param skip  How many rows in the file to skip (set 1 for header)
     * @return A list of string arrays containing all the Excel values
     */
    protected List<String[]> deserializeRecords(XSSFSheet sheet, int skip) {
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

    private static int getIndexById(XSSFSheet sheet, UUID id) {
        for (int row = 1; row < sheet.getLastRowNum(); row++){
            if(sheet.getRow(row).getCell(0).toString().equalsIgnoreCase(String.valueOf(id))){
                return row;
            }
        }
        return -1;
    }


    /**
     * Writes data to an Excel (XLSX) file.
     *
     * @param sheetName The name of the sheet to write data into.
     * @param newRecord String to write into file.
     * @param header
     * @param idxRecord Index to add / update record
     * @throws IOException If an I/O error occurs while writing to the Excel file.
     */
    protected void serializeRecord(String sheetName, String[] newRecord, String[] header, int idxRecord) throws IOException {
        // TODO: AVOID DRY (COMPARE WITH FILEIOHELPER)
        XSSFWorkbook workbook = new XSSFWorkbook(FileIOHelper.getFileInputStream(sheetName));
        XSSFSheet sheet = workbook.getSheetAt(0);
        if (sheet.getRow(0) == null) { // TODO: check this
            writeHeader(header, sheet.createRow(0));
        }
        writeRow(newRecord, sheet.createRow(idxRecord));
        try (FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName)) {
            // Write to file...
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    protected void serializeUpdate(String sheetName, String[] newRecord, UUID id) throws IOException {
        // TODO: AVOID DRY
        XSSFWorkbook workbook = new XSSFWorkbook(FileIOHelper.getFileInputStream(sheetName));
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Update row by id
        writeRow(newRecord, sheet.getRow(getIndexById(sheet, id)));
        try (FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName)) {
            // Write to file...
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    protected void deleteRecord(String sheetName, UUID id) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(FileIOHelper.getFileInputStream(sheetName));
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Remove row by id
        int rowIdx = getIndexById(sheet, id);
        if (rowIdx != -1) {
            sheet.removeRow(sheet.getRow(rowIdx));
            sheet.shiftRows(rowIdx + 1, sheet.getLastRowNum(), -1);
            try (FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName)) {
                // Write to file...
                workbook.write(fileOut);
            } finally {
                workbook.close();
            }
        }
    }

    protected void writeHeader(String[] header, Row row) {
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    protected void writeRow(String[] data, Row row) {
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data[i]);
        }
    }
}
