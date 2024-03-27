import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Menu {
    private Map<String, List<MenuItem>> menuByBranch;

    public Menu() {
        menuByBranch = new HashMap<String, List<MenuItem>>();
    }

    public void addItem(String branch, MenuItem item) {
        if (!menuByBranch.containsKey(branch)) {
            menuByBranch.put(branch, new ArrayList<MenuItem>());
        }
        menuByBranch.get(branch).add(item);
    }

    public void removeItem(String branch, MenuItem item) {
        if (menuByBranch.containsKey(branch)) {
            menuByBranch.get(branch).remove(item);
        }
    }

    public void updateItem(String branch, MenuItem oldItem, MenuItem newItem) {
        if (menuByBranch.containsKey(branch)) {
            List<MenuItem> menuItems = menuByBranch.get(branch);
            int index = menuItems.indexOf(oldItem);
            if (index != -1) {
                menuItems.set(index, newItem);
            }
        }
    }

    public List<MenuItem> getMenuByBranch(String branch) {
        return menuByBranch.getOrDefault(branch, new ArrayList<MenuItem>());
    }

    public class Branch {
    }
}

public class main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        // Add items to the menu
        MenuItem item1 = new MenuItem("Set Meal 1", 10.99);
        MenuItem item2 = new MenuItem("Set Meal 2", 12.99);
        MenuItem item3 = new MenuItem("Drink 1", 2.99);
        MenuItem item4 = new MenuItem("Drink 2", 3.99);

        menu.addItem("NTU", item1);
        menu.addItem("NTU", item2);
        menu.addItem("NTU", item3);
        menu.addItem("JP", item1);
        menu.addItem("JP", item2);
        menu.addItem("JP", item4);
        menu.addItem("JE", item1);
        menu.addItem("JE", item3);
        menu.addItem("JE", item4);

        // Remove an item from the menu
        menu.removeItem("NTU", item3);

        // Update an item in the menu
        MenuItem newItem = new MenuItem("Set Meal 3", 14.99);
        menu.updateItem("JP", item2, newItem);

        // Get menu by branch
        List<MenuItem> ntuMenu = menu.getMenuByBranch("NTU");
        List<MenuItem> jpMenu = menu.getMenuByBranch("JP");
        List<MenuItem> jeMenu = menu.getMenuByBranch("JE");

        // Print the menus
        System.out.println("NTU Menu:");
        for (MenuItem item : ntuMenu) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }

        System.out.println("\nJP Menu:");
        for (MenuItem item : jpMenu) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }

        System.out.println("\nJE Menu:");
        for (MenuItem item : jeMenu) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }
}
