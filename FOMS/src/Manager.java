public class Manager extends Staff  {
    // Constructor
    public Manager(String staffID, String name, Roles role, char gender, int age, String branch) {
        super(staffID, name, role, gender, age, branch);
    }

    /* STAFF MANAGEMENT PURPOSES */
    public void displayStaffList(Company company, String branchId, Staff.Roles auth) {
        Staff[] staffByBranch = company.getStaffList("branch", branchId, auth);
        for (int i = 0; i<staffByBranch.length; i++) {
            staffByBranch[i].getStaff();
        }
    }

    /* MENU ITEM PURPOSES */
    // TODO: UNCOMMENT WHEN MENU AND ITEM CLASSES ARE CREATED
    // Add menu item
//    public void addMenuItem(Menu menu, String name, double price, String description, String category) {
//        Item menuItem = new Item(name, price, description, category);
//        menu.addItem(menuItem);
//    }

    // Update menu item
//    public void updateMenuItem(Menu menu, String name) {
//        menu.updateItem(name);
//    }

    // Remove menu item
//    public void removeMenuItem(Menu menu, String name) {
//        menu.removeItem(name);
//    }
}
