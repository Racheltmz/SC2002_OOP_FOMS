package Menu;

public class Item {
    // Attributes
    private String name;
    private double price;
    private String branch;
    private String category;

    // Constructor
    public Item(String name, double price, String branch, String category) {
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


    //    public static void printItemsGroupedByCategory(ArrayList<Item> itemList) {
//        Map<String, ArrayList<Item>> categoryMap = groupItemsByCategory(itemList);
//        System.out.println("Menu items by category:");
//        printItemGroups(categoryMap);
//    }

    // Display menu items by branch
//    public static void printItemsGroupedByBranch(Company company) {
//        InputScanner sc = getInstance();
//        // Display branches
//        company.displayBranches();
//        // Get branches
//        ArrayList<Branch> branches = company.getBranches();
//        try {
//            // Get user's selection
//            System.out.println("Select Branch: ");
//            int branchIndex = sc.nextInt();
//            // Get branch name
//            String branch = branches.get(branchIndex-1).getBranchName();
//            Menu branchItems = company.getMenu(branch);
//            if (branchItems == null) {
//                System.out.println("No items found for the specified branch.");
//                return;
//            }
//            System.out.println("Menu items in branch " + branch + ":");
//            System.out.printf("| %-10s | %-20s | %-10s |\n", "Category", "Name", "Price ($)");
//            System.out.println("-".repeat(50));
//            for (Item item : Menu.getMenuItems()) {
//                System.out.printf("| %-10s | %-20s | %-10s |\n", item.getCategory(), item.getName(), item.getPrice());
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Invalid value, please enter again.");
//        }
//    }

//    public static void printItemsGroupedByName(ArrayList<Item> itemList) {
//        Map<String, ArrayList<Item>> nameMap = groupItemsByName(itemList);
//        System.out.println("Menu items by name:");
//        printItemGroups(nameMap);
//    }
//
//    public static Map<String, ArrayList<Item>> groupItemsByCategory(ArrayList<Item> itemList) {
//        Map<String, ArrayList<Item>> categoryMap = new HashMap<>();
//        for (Item item : itemList) {
//            categoryMap.computeIfAbsent(item.getCategory(), k -> new ArrayList<>()).add(item);
//        }
//        return categoryMap;
//    }
//
//    public static Map<String, ArrayList<Item>> groupItemsByBranch(ArrayList<Item> itemList) {
//        Map<String, ArrayList<Item>> branchMap = new HashMap<>();
//        for (Item item : itemList) {
//            branchMap.computeIfAbsent(item.getBranch(), k -> new ArrayList<>()).add(item);
//        }
//        return branchMap;
//    }
//
//    public static Map<String, ArrayList<Item>> groupItemsByName(ArrayList<Item> itemList) {
//        Map<String, ArrayList<Item>> nameMap = new HashMap<>();
//        for (Item item : itemList) {
//            nameMap.computeIfAbsent(item.getName(), k -> new ArrayList<>()).add(item);
//        }
//        return nameMap;
//    }
//
//    public static void printItemGroups(Map<String, ArrayList<Item>> itemMap) {
//        for (String key : itemMap.keySet()) {
//            System.out.println("Key: " + key);
//            for (Item item : itemMap.get(key)) {
//                System.out.println("Item: " + item.getName() + ", Price: " + item.getPrice());
//            }
//        }
//    }
}