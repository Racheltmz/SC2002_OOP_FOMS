import java.util.ArrayList;
import java.util.List;

public class Menu {
        private int menuId;
        private int customerId;
        private String name;
        private String category;
        private int quantity;
        private float price;
        private String branch;
        private String description;
        public Menu(int menuId, int customerId, String name, String category, float price, int quantity, String branch, String description) {
            super();
            this.menuId = menuId;
            this.customerId = customerId;
            this.name = name;
            this.category = category;
            this.price = price;
            this.branch = branch;
            this.quantity = quantity;
            this.description = description;
        }
    
    private List<Menu> items;
    public Menu() {
        this.items = new ArrayList<>();
    }

    public Menu(String name2, double price2, Menu.Branch branch2, String category2) {
    }

    public void addMenuItem(Menu item) {
        items.add(item);
    }

    public int getMenuId(){
        return menuId;
    }

    public void setMenuId(int menuId){
        this.menuId = menuId;
    }
    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
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

    public void assign(int menuId) {
    this.menuId = menuId;
    }

    public void addItemToOrder(int menuId, int customerId) {
        if (menuId < 1 || menuId > items.size()) {
            System.out.println("Invalid menu ID. Please choose a menu ID between 1 and " + items.size());
            return;
        }

        Menu menuItem = items.get(menuId - 1);
        if (menuItem.isOrdered()) {
            System.out.println("One more of the" + menuId + "is added to the order. ");
        }
    }

    public void removeItemFromOrder(int menuId) {
        if (menuId < 1 || menuId > items.size()) {
            System.out.println("Invalid menu ID. Please choose a menu ID between 1 and " + items.size());
            return;
        }

        Menu item = items.get(menuId - 1);
        if (item.isOrdered()) {
            item.removeOrderedItem();
            System.out.println("Menu Item " + menuId + " is removed from the cart.");
        } else {
            System.out.println("Menu Item " + menuId + " is not in the cart.");
        }
    }

    private void removeOrderedItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOrderedItem'");
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
