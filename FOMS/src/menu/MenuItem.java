package menu;

import exceptions.IllegalArgumentException;
import utils.IXlsxSerializable;

import java.io.Serializable;

// Menu Item details
public class MenuItem implements Serializable, IXlsxSerializable {
    // Attributes
//    private static final long serialVersionUID = 1L;
    private String name;
    private double price;
    private String branch;
    private String category;
    private String description;

    // Constructor
    public MenuItem(String name, double price, String branch, String category, String description) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.description = description;
    }

    // Serialization to XLSX
    public String[] toXlsx() {
        return new String[] { name, String.valueOf(price), branch, category, description };
//        return new String[] { String.valueOf(serialVersionUID), name, String.valueOf(price), branch, category, description };
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBranch() {
        return branch;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}