package authorisation;

import management.StaffRoles;

/**
 * View for the active staff records.
 */
public class ActiveView {
    /**
     * Display menu options for the staff's permissions.
     * @param menuMsg Options for the menu.
     * @param staffId Staff ID to print.
     * @param role Staff role to print.
     */
    public static void displayMenu(String menuMsg, String staffId, StaffRoles role) {
        System.out.println("-".repeat(30));
        System.out.printf("StaffID: %s\nRole: %s\n", staffId, role);
        System.out.println(menuMsg);
        System.out.println("-".repeat(30));
        System.out.print("Enter option: ");
    }
}
