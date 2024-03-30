import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Item> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    public void addItem(Item menuItem) {
        menuItems.add(menuItem);
    }

    public void updateItem(Item updatedItem) {
        for (int i = 0; i < menuItems.size(); i++) {
            Item currentItem = menuItems.get(i);
            if (currentItem.getName().equals(updatedItem.getName())) {
                menuItems.set(i, updatedItem);
                break;
            }
        }
    }

    public void removeItem(Item itemToRemove) {
        menuItems.removeIf(item -> item.getName().equals(itemToRemove.getName()));
    }

    public List<Item> getItemsByBranch(String branch) {
        List<Item> itemsByBranch = new ArrayList<>();
        for (Item item : menuItems) {
            if (item.getBranch().equals(branch)) {
                itemsByBranch.add(item);
            }
        }
        return itemsByBranch;
    }

    public List<Item> getItemsByCategory(String category) {
        List<Item> itemsByCategory = new ArrayList<>();
        for (Item item : menuItems) {
            if (item.getCategory().equals(category)) {
                itemsByCategory.add(item);
            }
        }
        return itemsByCategory;
    }

    public List<Item> getItemsByBranchAndCategory(String branch, String category) {
        List<Item> itemsByBranchAndCategory = new ArrayList<>();
        for (Item item : menuItems) {
            if (item.getBranch().equals(branch) && item.getCategory().equals(category)) {
                itemsByBranchAndCategory.add(item);
            }
        }
        return itemsByBranchAndCategory;
    }
}
