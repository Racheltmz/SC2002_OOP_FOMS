package io;

public interface IXlsxSerializable {
    /**
     * Method to get the data to be written to XLSX file.
     *
     * @return Array of Strings representing data to write to XLSX file
     */
    String[] toXlsx();
}
