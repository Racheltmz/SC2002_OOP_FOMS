package menu;


import utils.IXlsxSerializable;

// Menu Item details
public class MenuItem implements IXlsxSerializable{
    // Attributes
    private String name;
    private double price;
    private String branch;
    private String category;

    // Constructor
    public MenuItem(String name, double price, String branch, String category) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
    }

    
   // Constructor for deserialization from XLSX data
   public MenuItem(String[] data) {
    if (data.length != 4) {
        throw new IllegalArgumentException("Invalid data format for MenuItem");
    }
    this.name = data[0];
    this.price = Double.parseDouble(data[1]);
    this.branch = data[2];
    this.category = data[3];
}

// Serialization to XLSX
public String[] toXlsx(){
    return new String[]{name, String.valueOf(price), branch, category};
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
}