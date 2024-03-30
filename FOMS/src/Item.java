import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Item {
    private String name;
    private double price;
    private String branch;
    private String category;

    public Item(String name, double price, String branch, String category) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBranch() {
        return branch;
    }

    public String getCategory() {
        return category;
    }

    public static void printItemsGroupedByCategory(List<Item> itemList) {
        Map<String, List<Item>> categoryMap = groupItemsByCategory(itemList);
        System.out.println("Menu items by category:");
        printItemGroups(categoryMap);
    }



    public static void printItemsGroupedByBranch(List<Item> itemList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a branch: \n1 for JE \n2 for JP \n3 for NTU ");
        int branchChoice = scanner.nextInt();
        scanner.close();

        String branch;
        switch (branchChoice) {
            case 1:
                branch = "JE";
                break;
            case 2:
                branch = "JP";
                break;
            case 3:
                branch = "NTU";
                break;
            default:
                System.out.println("Invalid branch choice.");
                return;
        }

        Map<String, List<Item>> branchMap = groupItemsByBranch(itemList);
        List<Item> branchItems = branchMap.getOrDefault(branch, null);

        if (branchItems == null) {
            System.out.println("No items found for the specified branch.");
            return;
        }

        System.out.println("Menu items in branch " + branch + ":");
        for (Item item : branchItems) {
            System.out.println(" category: " + item.getCategory()+ " " + item.getName() + ":  $" + item.getPrice() );
        }
    }


    public static void printItemsGroupedByName(List<Item> itemList) {
        Map<String, List<Item>> nameMap = groupItemsByName(itemList);
        System.out.println("Menu items by name:");
        printItemGroups(nameMap);
    }

    public static Map<String, List<Item>> groupItemsByCategory(List<Item> itemList) {
        Map<String, List<Item>> categoryMap = new HashMap<>();
        for (Item item : itemList) {
            categoryMap.computeIfAbsent(item.getCategory(), k -> new ArrayList<>()).add(item);
        }
        return categoryMap;
    }

    public static Map<String, List<Item>> groupItemsByBranch(List<Item> itemList) {
        Map<String, List<Item>> branchMap = new HashMap<>();
        for (Item item : itemList) {
            branchMap.computeIfAbsent(item.getBranch(), k -> new ArrayList<>()).add(item);
        }
        return branchMap;
    }

    public static Map<String, List<Item>> groupItemsByName(List<Item> itemList) {
        Map<String, List<Item>> nameMap = new HashMap<>();
        for (Item item : itemList) {
            nameMap.computeIfAbsent(item.getName(), k -> new ArrayList<>()).add(item);
        }
        return nameMap;
    }

    public static void printItemGroups(Map<String, List<Item>> itemMap) {
        for (String key : itemMap.keySet()) {
            System.out.println("Key: " + key);
            for (Item item : itemMap.get(key)) {
                System.out.println("Item: " + item.getName() + ", Price: " + item.getPrice());
            }
        }
    }

    
    
}
