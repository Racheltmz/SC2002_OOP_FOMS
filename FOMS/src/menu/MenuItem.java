package menu;

import io.IXlsxSerializable;

import java.util.UUID;

/**
 * MenuItem class represents an item in the Menu of a Branch.
 */
public class MenuItem implements IXlsxSerializable {
    // Attributes
    private UUID id = UUID.randomUUID();
    private String name;
    private double price;
    private String branch;
    private String category;
    private String description;

    /**
     * Constructs a MenuItem.
     *
     * @param name Name of the MenuItem.
     * @param price Price of the MenuItem.
     * @param branch Branch of the MenuItem.
     * @param category Category of the MenuItem.
     * @param description Description of the MenuItem.
     */
    public MenuItem(String name, double price, String branch, String category, String description) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.description = description;
    }

    /**
     * Constructs a MenuItem with a Record ID.
     *
     * @param id Record ID of the MenuItem.
     * @param name Name of the MenuItem.
     * @param price Price of the MenuItem.
     * @param branch Branch of the MenuItem.
     * @param category Category of the MenuItem.
     * @param description Description of the MenuItem.
     */
    public MenuItem(UUID id, String name, double price, String branch, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.description = description;
    }

    /**
     * Convert MenuItem details to String for serialisation.
     * @return String array of this MenuItem's details.
     */
    public String[] toXlsx() {
        return new String[] { String.valueOf(id), name, String.valueOf(price), branch, category, description };
    }

    /**
     * Get the UUID of this MenuItem.
     *
     * @return This MenuItem's Record ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get the name of this MenuItem.
     *
     * @return This MenuItem's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this MenuItem.
     *
     * @param name The name to set.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Get the price of this MenuItem.
     *
     * @return This MenuItem's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of this MenuItem.
     *
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the Branch of this MenuItem.
     *
     * @return This MenuItem's Branch.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Get the category of this MenuItem.
     *
     * @return This MenuItem's category.
     */
    public String getCategory() { return category; }

    /**
     * Set the category of this MenuItem.
     *
     * @param category The category to set.
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Get the description of this MenuItem.
     *
     * @return This MenuItem's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this MenuItem.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}