package Management;

import Order.OrderQueue;
import Order.OrderStatus;

import static Authentication.Authentication.setNewPassword;
import static Authentication.Hashing.genHash;

public class Staff {
    private String staffID;
    private String name;
    private String password;
    private StaffRoles role;
    private String gender;
    private int age;
    private String branch;

    public Staff(String staffID, String name, StaffRoles role, String gender, int age, String branch) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.password = genHash("password");
    }

    /* GETTERS AND SETTERS */
    public String getStaffID() {
        return this.staffID;
    }

    public StaffRoles getRole() {
        return this.role;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getBranch() {
        return this.branch;
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
    public void getNewOrders(String branchName, OrderQueue queue) {
        queue.displayNewOrders(branchName);
    }

    // Specific order
    public void getOrderDetails(OrderQueue queue) {
        queue.displayOrder();
    }

    // Update order status to ready to pickup for Test Case 10 and 12
    public void setOrderReady(OrderQueue queue) {
        queue.updateStatus(OrderStatus.NEW, OrderStatus.READY);
    }
}
