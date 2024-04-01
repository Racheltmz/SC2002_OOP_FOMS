package Validation;

import Menu.Item;

import java.util.ArrayList;

import static Validation.ValidateDataType.validateInt;

public class ValidateIndex {
    public static Item validateIndex(ArrayList<Item> itemList, int index) {
        boolean success = false;
        Item item = null;
        do {
            try {
                // Get item to update by name
                int itemIndex = validateInt("Enter the number of the item you want to update: ") - 1;
                System.out.println(itemIndex);
                item = itemList.get(itemIndex);
                success = true;
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Please enter a valid number.");
            }
        } while (!success);
        return item;
    }
}
