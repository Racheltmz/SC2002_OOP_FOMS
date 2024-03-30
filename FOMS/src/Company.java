import java.util.ArrayList;
import java.util.Arrays;

public class Company {
    private Staff[] staffList;
    private Item[] menuList;
    // private Branch[] branches TODO: COMMENT OUT ONCE BRANCH CLASS IS ADDED
    // private Payment[] paymentList; TODO: COMMENT OUT ONCE PAYMENT CLASS IS ADDED

    // TODO: UPDATE ONCE BRANCH AND PAYMENT CLASSES ARE ADDED
    // Constructor for Company class
    public Company(Staff[] staffList, Item[] menuList) {
        this.staffList = staffList;
        this.menuList = menuList; 
    }

    /* STAFF PURPOSES */
    // Filter list of staff members for strings (only branch field)
    public Staff[] getStaffList(String filter, String filterVal, Staff.Roles auth) {
        // Initialise new staff list
        Staff[] filteredStaff = new Staff[0];
        // Authenticate staff else return empty list
        if (auth == Staff.Roles.ADMIN || auth == Staff.Roles.MANAGER) {
            // Get number of staff members
            int numStaff = this.staffList.length;
            // Counter for indexing
            int counter = 0;
            // Check if the filter is "branch", else it returns an empty array
            if (filter == "branch") {
                for (int i = 0; i < numStaff; i++) {
                    if (this.staffList[i].getBranch() == filterVal) {
                        filteredStaff[counter] = this.staffList[i];
                        counter++;
                    }
                }
            }
        } else {
            System.out.println("Invalid access.");
        }
        return filteredStaff;
    }

    // Filter list of staff members for roles enum (only role field)
    public Staff[] getStaffList(String filter, Staff.Roles filterVal, Staff.Roles auth) {
        // Initialise new staff list
        Staff[] filteredStaff = new Staff[0];
        // Authenticate staff else return empty list
        if (auth == Staff.Roles.ADMIN) {
            // Get number of staff members
            int numStaff = this.staffList.length;
            // Counter for indexing
            int counter = 0;
            // Check if the filter is "role", else it returns an empty array
            if (filter == "role") {
                for (int i = 0; i < numStaff; i++) {
                    if (this.staffList[i].getRole() == filterVal) {
                        filteredStaff[counter] = this.staffList[i];
                        counter++;
                    }
                }
            }
        } else {
            System.out.println("Invalid access.");
        }
        return filteredStaff;
    }

    // Filter list of staff members for character (only gender field)
    public Staff[] getStaffList(String filter, char filterVal, Staff.Roles auth) {
        // Initialise new staff list
        Staff[] filteredStaff = new Staff[0];
        // Authenticate staff else return empty list
        if (auth == Staff.Roles.ADMIN) {
            // Get number of staff members
            int numStaff = this.staffList.length;
            // Counter for indexing
            int counter = 0;
            // Check if the filter is "gender", else it returns an empty array
            if (filter == "gender") {
                for (int i = 0; i < numStaff; i++) {
                    if (this.staffList[i].getGender() == filterVal) {
                        filteredStaff[counter] = this.staffList[i];
                        counter++;
                    }
                }
            }
        } else {
            System.out.println("Invalid access.");
        }
        return filteredStaff;
    }

    // Filter list of staff members for integer (only age field)
    public Staff[] getStaffList(String filter, int filterVal, Staff.Roles auth) {
        // Initialise new staff list
        Staff[] filteredStaff = new Staff[0];
        // Authenticate staff else return empty list
        if (auth == Staff.Roles.ADMIN) {
            // Get number of staff members
            int numStaff = this.staffList.length;
            // Counter for indexing
            int counter = 0;
            // Check if the filter is "age", else it returns an empty array
            if (filter == "age") {
                for (int i = 0; i < numStaff; i++) {
                    if (this.staffList[i].getAge() == filterVal) {
                        filteredStaff[counter] = this.staffList[i];
                        counter++;
                    }
                }
            }
        } else {
            System.out.println("Invalid access.");
        }
        return filteredStaff;
    }

    public int getNumManagers(Staff.Roles auth) {
        Staff[] managerList = getStaffList("role", Staff.Roles.MANAGER, auth);
        return managerList.length;
    }
public Item[] getMenuList(String filter, String filterVal, Staff.Roles auth) {
    // Initialize new menu list
    Item[] filteredItem = new Item[0];
    // Authenticate menu else return empty list
    if (auth == Staff.Roles.ADMIN || auth == Staff.Roles.MANAGER) {
        // Get number of menu items
        int numItems = this.menuList.length;
        // Counter for indexing
        int counter = 0;
        // Check if the filter is "branch", else it returns an empty array
        if (filter.equals("branch")) { // Use equals method to compare strings
            for (int i = 0; i < numItems; i++) {
                if (this.menuList[i].getBranch().equalsIgnoreCase(filterVal)) {
                    // Corrected the array name to filteredMenu
                    // Corrected array assignment
                    filteredItem = Arrays.copyOf(filteredItem, filteredItem.length + 1);
                    filteredItem[counter] = this.menuList[i];
                    counter++;
                }
            }
        }
    } else {
        System.out.println("Invalid access.");
    }
    return filteredItem;
}

public int getNumMenuItems() {
    // Assuming 'auth' is a member variable accessible in this method
    Staff.Roles auth = Staff.Roles.MANAGER;
    Item[] itemList = getMenuList("branch", "exampleBranch", auth); // Corrected the filter parameter
    return itemList.length; // Return the length of the filtered menu list
}

    

    /* BRANCHES PURPOSES */
//    public Branch[] getBranches() {
//        return branches;
//    }

//    protected void addBranch(Branch branch) {
//        // Get number of branches
//        int numBranches = this.branches.length;
//        this.branches[numBranches] = branch;
//    }

//    protected void deleteBranch(String branchName) {
//        // Get number of branches
//        int numBranches = this.branches.length;
//        // Remove branches
//        for (int i = 0, j = 0; i < numBranches; i++) {
//            if (this.branches[i].name != branchName) {
//                this.branches[j] = this.branches[i];
//                j++;
//            }
//        }
//    }

    /* PAYMENT PURPOSES */
//    public Payment[] getPaymentList() {
//        return this.paymentList;
//    }
}
