import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static int menuIdCounter = 1; // Initialize menuIdCounter
    private int menuId;
    private int customerId;
    private String name;
    private String category;
    private int quantity;
    private float price;
    private String branch;
    private String description;

    private static List<Menu> items = new ArrayList<>(); // Static list to store menu items

    public Menu(int customerId, String name, String category, float price, int quantity, String branch, String description) {
        this.menuId = menuIdCounter++;
        this.customerId = customerId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.branch = branch;
        this.quantity = quantity;
        this.description = description;
        items.add(this); // Add the created menu item to the static list
    }
    

    public void addMenuItem(Menu item) {
        items.add(item);
    }

    public void setMenuId(int menuId){
        this.menuId = menuId;
    }
    public int getMenuId(){
        return menuId;
    }

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity (int quantity){
        this.quantity = quantity;
    }

    public String getCategory(){
        return category;
    }

    public void setBranch ( String branch){
        this.branch = branch;
    }

    public String getBranch(){
        return branch;
    }

    public void setDescription( String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public boolean isOrdered() {
    return menuId != -1;
    }

    public void removeOrderedItem() {
        menuId = -1; // Reset menu ID to indicate that the item is not ordered
        customerId = -1; // Reset customer ID
        System.out.println("Item removed from the order.");
    }
    

    @SuppressWarnings("resource")
    public void displayMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Branch:  ");
        String bran = scan.nextLine();
        if (bran.equalsIgnoreCase("NTU")) {
            System.out.println("NTU branch menu as follows:");
            for (Menu item : items) {
                if (item.getBranch().equalsIgnoreCase(bran)) {
                    System.out.println(item.getMenuId() + ": " + item.getName());
                }
            }
        } else if (bran.equalsIgnoreCase("JP")) {
            System.out.println("JP branch menu as follows:");
            for (Menu item : items) {
                if (item.getBranch().equalsIgnoreCase(bran)) {
                    System.out.println(item.getMenuId() + ": " + item.getName());
                }
            }
        } else if (bran.equalsIgnoreCase("JE")) {
            System.out.println("JE branch menu as follows:");
            for (Menu item : items) {
                if (item.getBranch().equalsIgnoreCase(bran)) {
                    System.out.println(item.getMenuId() + ": " + item.getName());
                }
            }
        }
    }
    
    public void displayOrder(int customerId) {
        boolean found = false;
        System.out.println("Orders for Customer ID: " + customerId);
        for (Menu item : items) {
            if (item.isOrdered()) {
                System.out.println("Menu ID: " + item.getMenuId() + ", Name: " + item.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No orders found for Customer ID: " + customerId);
        }
    }
    


    public void addItemToOrder(int menuId, int customerId) {
        if (menuId < 1 || menuId > items.size()) {
            System.out.println("Invalid menu ID. Please choose a menu ID between 1 and " + items.size());
            return;
        }
    
        Menu selectedItem = null;
        for (Menu item : items) {
            if (item.getMenuId() == menuId) {
                selectedItem = item;
                break;
            }
        }
    
        if (selectedItem != null) {
            if (selectedItem.isOrdered()) {
                System.out.println("One more of the " + selectedItem.getName() + " is added to the order.");
                //Order needs to be linked here
            } else {
                selectedItem.setMenuId(menuId); // Assign the menu ID
                selectedItem.setCustomerId(customerId); // Assign the customer ID
                System.out.println(selectedItem.getName() + " is added to the order.");
            }
        } else {
            System.out.println("Invalid menu ID.");
        }
    }
    
    public void removeItemFromOrder(int menuId) {
        if (menuId < 1 || menuId > items.size()) {
            System.out.println("Invalid menu ID. Please choose a menu ID between 1 and " + items.size());
            return;
        }
    
        Menu selectedItem = null;
        for (Menu item : items) {
            if (item.getMenuId() == menuId) {
                selectedItem = item;
                break;
            }
        }
    
        if (selectedItem != null) {
            if (selectedItem.isOrdered()) {
                // removing item from the order
                selectedItem.removeOrderedItem(); 
                System.out.println("Item " + selectedItem.getName() + " removed from the order.");
            } else {
                System.out.println("Item " + selectedItem.getName() + " is not in the order.");
            }
        } else {
            System.out.println("Invalid menu ID.");
        }
    }
    


    public void showOrderedItems(boolean byMenuId) {
            System.out.println("The order items are as follows:");
            for (Menu item : items) {
                if (item.isOrdered()) {
                    System.out.println("Menu ID: " + item.getMenuId() + " assigned to Customer ID: " + item.getCustomerId());
                }
            }
    }

    public interface Branch {
    }

}
