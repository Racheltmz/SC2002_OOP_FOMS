import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Item> items;
    private int numOrderedItems;

    public Menu() {
        this.items = new ArrayList<>();
        this.numOrderedItems = 0;
    }

    public void addMenuItem(Item item) {
        items.add(item);
    }

    public void addItemToOrder(int menuId, int customerId) {
        if (menuId < 1 || menuId > items.size()) {
            System.out.println("Invalid menu ID. Please choose a menu ID between 1 and " + items.size());
            return;
        }

        Item menuItem = items.get(menuId - 1);
        if (menuItem.isOrdered()) {
            System.out.println("One more of the" + menuId + "is added to the order. ");
        }
    }

    public void removeItemFromOrder(int menuId) {
        if (menuId < 1 || menuId > items.size()) {
            System.out.println("Invalid menu ID. Please choose a menu ID between 1 and " + items.size());
            return;
        }

        Item item = items.get(menuId - 1);
        if (item.isOrdered()) {
            item.removeOrderedItem();
            numOrderedItems--;
            System.out.println("Menu Item " + menuId + " is removed from the cart.");
        } else {
            System.out.println("Menu Item " + menuId + " is not in the cart.");
        }
    }

    public void showOrderedItems(boolean byMenuId) {
            System.out.println("The order items are as follows:");
            for (Item item : items) {
                if (item.isOrdered()) {
                    System.out.println("Menu ID: " + item.getMenuId() + " assigned to Customer ID: " + item.getCustomerId());
                }
            }
    }

}
