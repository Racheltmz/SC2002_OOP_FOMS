package exceptions;

public class MenuItemNotFoundException extends Exception{
    public MenuItemNotFoundException() {
        super("Menu Item not found!");
    }
    public MenuItemNotFoundException(String message){
        super(message);
    }
}
