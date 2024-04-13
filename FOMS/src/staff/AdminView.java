package staff;

import utils.InputScanner;

public class AdminView {
    InputScanner sc = InputScanner.getInstance();

    public StaffFilterOptions getFieldToFilter() {
        System.out.println("Enter the number for the field you want to filter the staff list by: ");
        System.out.println("1. Branch");
        System.out.println("2. Role");
        System.out.println("3. Gender");
        System.out.println("4. Age");
        int adminChoice = sc.nextInt();
        return StaffFilterOptions.of(adminChoice);
    }
}
