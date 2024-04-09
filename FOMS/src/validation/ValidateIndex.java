package validation;

import menu.MenuItem;

import java.util.ArrayList;

import exceptions.ArrayIndexOutOfBoundsException;

import static validation.ValidateDataType.validateInt;

public class ValidateIndex {
    public static MenuItem validateIndex(ArrayList<MenuItem> itemList, int index) throws ArrayIndexOutOfBoundsException {
        boolean success = false;
        MenuItem item = null;
        do {
                // Get item to update by name
                int itemIndex = validateInt("Enter the number of the item you want to update: ") - 1;
                System.out.println(itemIndex);
                item = itemList.get(itemIndex);
                success = true;
            
        } while (!success);
        return item;
    }
}
