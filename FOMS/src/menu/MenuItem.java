package menu;

// Menu Item details
public class MenuItem {
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