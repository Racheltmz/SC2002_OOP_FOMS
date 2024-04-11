package menu;

import utils.IXlsxSerializable;

import java.util.UUID;

// Menu Item details
public class MenuItem implements IXlsxSerializable {
    // Attributes
    private UUID id = UUID.randomUUID();
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

    public MenuItem(UUID id, String name, double price, String branch, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.description = description;
    }

    // Serialization to XLSX
    public String[] toXlsx() {
//        return new String[] { name, String.valueOf(price), branch, category, description };
        return new String[] { String.valueOf(id), name, String.valueOf(price), branch, category, description };
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

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