package staff;

import branch.Branch;
import order.OrderQueue;
import utils.IXlsxSerializable;
import utils.InputScanner;

import static authentication.Hashing.genHash;

import java.util.UUID;

// Staff Details
public class Staff implements IXlsxSerializable {
    private UUID id = UUID.randomUUID();
    private String staffID;
    private String name;
    private StaffRoles role;
    private char gender;
    private int age;
    private Branch branch;
    private String password = genHash("password");
    private final StaffView staffView = new StaffView();

    public Staff(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
    }

    public Staff(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        this.id = id;
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
    }

    // Serialization to XLSX
    public String[] toXlsx() {
        return new String[] { String.valueOf(id), name, staffID, role.getAcronym(), String.valueOf(gender), String.valueOf(age), branch.getName() };
    }

    /**
     * Get record ID
     * @return ID
     */
    public UUID getId() {
        return id;
    }

    public String getStaffID() {
        return this.staffID;
    }

    public String getName() {
        return name;
    }

    public StaffRoles getRole() {
        return this.role;
    }

    public void setRole(StaffRoles role) {
        this.role = role;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* MANAGING PERSONAL DETAILS PURPOSES */
    /**
     * Verify if the user entered the right password for the account
     *
     * @param password Password inputted
     * @return Boolean value of whether the password matches
     */
    public boolean verifyPassword(String password) {
        return password.equals(this.getPassword());
    }

    /**
     * Change password
     */
    // TODO: AFREEN (whether to create a view)
    public void changePassword() {
        InputScanner sc = InputScanner.getInstance();
        System.out.print("\nPlease enter new password: ");
        String newPassword = sc.next();
        // Check if user entered a password or if they entered the same password as they had in the past
        while (newPassword.isEmpty() || this.verifyPassword(genHash(newPassword))) {
            System.out.println("Password change unsuccessful, please try again. Ensure that it differs from your previous password.");
            System.out.print("Please enter new password: ");
            newPassword = sc.next();
        }
        setPassword(genHash(newPassword));
        System.out.println("Password successfully changed!");
    }

    /* MANAGING STAFF PURPOSES */
    // Display staff details
    // TODO: AFREEN (whether to create a view)
    public void printStaffDetails() {
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-5s |\n",
                this.staffID, this.name, this.role, this.gender, this.age);
    }

    /* MANAGING ORDER PURPOSES */
    // New orders for Test Case 9 and 11
    public void getNewOrders(Branch branch) {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayNewOrders(branch.getName());
    }

    // Specific order
    public void getOrderDetails() {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayOrder();
    }

    // Update order status to ready to pickup for Test Case 10 and 12
    public void setOrderReady() {
        OrderQueue queue = OrderQueue.getInstance();
        queue.updateStatusToReady();
    }
}
