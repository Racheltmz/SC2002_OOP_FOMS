package io;

import exceptions.ItemNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import payment.Payment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static io.FileIOHelper.getFileInputStream;

@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
public class BaseXlsxHelper {
    // Get Excel spreadsheet by filename
    private static XSSFSheet getSheet(String filePath) {
        XSSFSheet sheet = null;
        try {
            // Reading file from local directory
            FileInputStream file = getFileInputStream(filePath);

            // Create Workbook instance holding reference to .xlsx file
            assert file != null;
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first sheet from the workbook
            sheet = workbook.getSheetAt(0);

            // Closing workbook and file output streams
            workbook.close();
            file.close();
        } catch (IOException | NullPointerException e) {
            System.out.println("Unable to load records. Please check the file storage.");
        }
        return sheet;
    }

    /**
     * Reads data from an Excel (XLSX) file and returns a List of String arrays representing rows and columns.
     *
     * @param sheetName The Sheet object representing the Excel sheet to read from.
     * @param skip  How many rows in the file to skip (set 1 for header)
     * @return A list of string arrays containing all the Excel values
     */
    protected List<String[]> deserializeRecords(String sheetName, int numCols, int skip) {
        List<String[]> result = new ArrayList<>();
        XSSFSheet sheet = getSheet(sheetName);
        if (sheet != null) {
            for (int i = skip; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    List<String> rowData = new ArrayList<>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            if (!Objects.equals(cell.toString(), ""))
                                rowData.add(cell.toString());
                        } else {
                            rowData.add(""); // Empty cell
                        }
                    }
                    String[] record = rowData.toArray(new String[0]);
                    if (record.length == numCols)
                        result.add(record);
                }
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
        try {
            for (int row = 1; row <= sheet.getLastRowNum(); row++) {
                if (sheet.getRow(row).getCell(0).toString().equalsIgnoreCase(String.valueOf(id))) {
                    return row;
                }
            }
            throw new ItemNotFoundException("Record cannot be found in storage.");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    /**
     * Get the row index from the Excel spreadsheet based on the record's id
     *
     * @param sheet Excel spreadsheet to search
     * @param id id to match
     * @return Row index of the record
     */
    private static int getIndexById(XSSFSheet sheet, String id) {
        try {
            for (int row = 1; row <= sheet.getLastRowNum(); row++){
                if(sheet.getRow(row).getCell(0).toString().equalsIgnoreCase(id)){
                    return row;
                }
            }
            throw new ItemNotFoundException("Record cannot be found in storage.");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    /**
     * Get the row index from the Excel spreadsheet based on the record's id
     *
     * @param sheet Excel spreadsheet to search
     * @param payment payment to match
     * @return Row index of the record
     */
    private static int getIndexByPayment(XSSFSheet sheet, Payment payment) {
        try {
            for (int row = 1; row <= sheet.getLastRowNum(); row++){
                if(sheet.getRow(row).getCell(0).toString().equalsIgnoreCase(payment.getCategory()) &&
                        sheet.getRow(row).getCell(1).toString().equalsIgnoreCase(payment.getPaymentMethod())){
                    return row;
                }
            }
            throw new ItemNotFoundException("Record cannot be found in storage.");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    protected void serializeHeader(String sheetName, String[] header) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(Objects.requireNonNull(getFileInputStream(sheetName)))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            writeHeader(header, sheet.createRow(0));
            FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName);
            // Write to file
            workbook.write(fileOut);
        } catch (IOException | NullPointerException e) {
            System.out.println("Unable to add header. Please check the file storage.");
        }
    }


    /**
     * Adds a data record into the Excel File
     *
     * @param sheetName The name of the sheet to write data into
     * @param newRecord String to write into file
     * @param idxRecord Index to add / update record
     */
    protected void serializeRecord(String sheetName, String[] newRecord, int idxRecord) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(Objects.requireNonNull(getFileInputStream(sheetName)))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            writeRow(newRecord, sheet.createRow(idxRecord+1));
            FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName);
            // Write to file
            workbook.write(fileOut);
        } catch (IOException | NullPointerException e) {
            System.out.println("Unable to add record. Please check the file storage.");
        }
    }

    /**
     * Updates a data record based on the ID
     *
     * @param sheetName Sheet to update
     * @param record Record to update
     * @param id ID of the record to be updated
     */
    protected void serializeUpdate(String sheetName, String[] record, UUID id) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(Objects.requireNonNull(getFileInputStream(sheetName)))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Update row by id
            writeRow(record, sheet.getRow(getIndexById(sheet, id)));
            FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName);
            // Write to file
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println("Unable to update record. Please check the file storage.");
        }
    }

    protected void serializeUpdate(String sheetName, String[] record, String id) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(Objects.requireNonNull(getFileInputStream(sheetName)))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Update row by id
            writeRow(record, sheet.getRow(getIndexById(sheet, id)));
            FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName);
            // Write to file
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println("Unable to update record. Please check the file storage.");
        }
    }

    /**
     * Deletes a record based on the ID.
     *
     * @param sheetName Sheet to delete from.
     * @param id ID of record to delete.
     */
    protected void deleteRecord(String sheetName, UUID id) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(Objects.requireNonNull(getFileInputStream(sheetName)))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Remove row by id
            int rowIdx = getIndexById(sheet, id);
            if (rowIdx != -1) {
                sheet.removeRow(sheet.getRow(rowIdx));
                if (rowIdx < sheet.getLastRowNum()) {
                    sheet.shiftRows(rowIdx + 1, sheet.getLastRowNum(), -1);
                }
                FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName);
                // Write to file
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            System.out.println("Unable to delete record. Please check the file storage.");
        }
    }

    /**
     * Deletes a record based on the Payment object.
     *
     * @param sheetName Sheet to delete from.
     * @param payment Payment record to delete.
     */
    protected void deleteRecord(String sheetName, Payment payment) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(Objects.requireNonNull(getFileInputStream(sheetName)))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Remove row by id
            int rowIdx = getIndexByPayment(sheet, payment);
            if (rowIdx != -1) {
                sheet.removeRow(sheet.getRow(rowIdx));
                if (rowIdx < sheet.getLastRowNum()) {
                    sheet.shiftRows(rowIdx + 1, sheet.getLastRowNum(), -1);
                }
                FileOutputStream fileOut = FileIOHelper.getFileOutputStream(sheetName);
                // Write to file
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            System.out.println("Unable to delete record. Please check the file storage.");
        }
    }

    /**
     * Create a header
     *
     * @param header Header to add to the top of the file
     * @param row Row to add the record to
     */
    private void writeHeader(String[] header, Row row) {
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
    private void writeRow(String[] data, Row row) {
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data[i]);
        }
    }
}
