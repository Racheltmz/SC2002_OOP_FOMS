package authorisation;

public class ActiveView {
    public static void displayMenu(String menuMsg) {
        System.out.println();
        System.out.println("-".repeat(30));
        System.out.println("Please select option: ");
        System.out.println(menuMsg);
        System.out.println("-".repeat(30));
        System.out.print("Enter option: ");
    }
}
