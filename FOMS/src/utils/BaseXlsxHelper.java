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

    /**
     * Get the row index from the Excel spreadsheet based on the record's UUID
     *
     * @param sheet Excel spreadsheet to search
     * @param id UUID to match
     * @return Row index of the record
     */
    private static int getIndexById(XSSFSheet sheet, UUID id) {
        for (int row = 1; row < sheet.getLastRowNum(); row++){
            if(sheet.getRow(row).getCell(0).toString().equalsIgnoreCase(String.valueOf(id))){
                return row;
            }
        }
        return -1;
    }


    /**
     * Adds a data record into the Excel File
     *
     * @param sheetName The name of the sheet to write data into
     * @param newRecord String to write into file
     * @param header Headers for the file
     * @param idxRecord Index to add / update record
     * @throws IOException If an I/O error occurs while writing to the Excel file
     */
    protected void serializeRecord(String sheetName, String[] newRecord, String[] header, int idxRecord) throws IOException {
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


    /**
     * Updates a data record based on the ID
     *
     * @param sheetName Sheet to update
     * @param record Record to update
     * @param id ID of the record to be updated
     * @throws IOException Error if there is a problem with the IO process
     */
    protected void serializeUpdate(String sheetName, String[] record, UUID id) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(FileIOHelper.getFileInputStream(sheetName));
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Update row by id
        writeRow(record, sheet.getRow(getIndexById(sheet, id)));
        try (FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName)) {
            // Write to file...
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    /**
     * Deletes a record based on the ID
     *
     * @param sheetName Sheet to delete
     * @param id ID of record to delete
     * @throws IOException Error if there is a problem with the IO process
     */
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

    /**
     * Create a header
     *
     * @param header Header to add to the top of the file
     * @param row Row to add the record to
     */
    protected void writeHeader(String[] header, Row row) {
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    /**
     * Create a new row
     *
     * @param data Data record to add
     * @param row Row to add the record to
     */
    protected void writeRow(String[] data, Row row) {
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data[i]);
        }
    }
}
