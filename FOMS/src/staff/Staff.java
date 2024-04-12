package staff;

import branch.Branch;
import order.OrderQueue;
import utils.IXlsxSerializable;

import static authentication.Authentication.setNewPassword;
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
    private final StaffView staffView;

    public Staff(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.staffView = new StaffView();
    }

    public Staff(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        this.id = id;
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.staffView = new StaffView();
    }

    // Serialization to XLSX
    public String[] toXlsx() {
        return new String[] { String.valueOf(id), name, staffID, role.toString(), String.valueOf(gender), String.valueOf(age), branch.getName() };
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

    public void setStaffID(String staffID) {
        this.staffID = staffID;
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
    public void changePassword(Staff activeStaff) {
        setNewPassword(activeStaff);
    }

    /* MANAGING STAFF PURPOSES */
    // Display staff details
    public void getStaffDetails() {
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
