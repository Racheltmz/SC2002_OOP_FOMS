package Management;

import Order.Order;
import Order.OrderQueue;
import java.util.ArrayList;

import static Authentication.Hashing.genHash;

public class Staff {
    public enum Roles { STAFF, MANAGER, ADMIN}
    private String staffID;
    private String name;
    private String password;
    private Roles role;
    private char gender;
    private int age;
    private String branch;

    public Staff(String staffID, String name, Roles role, char gender, int age, String branch) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.password = genHash("password");
    }

    public String getStaffID() {
        return this.staffID;
    }

    public Roles getRole() {
        return this.role;
    }
    public char getGender() {
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

    // Display staff details
    public void displayStaffDetails() {
        System.out.println(this.staffID + ' ' + this.name + ' ' + this.role + ' ' +
                this.gender + ' ' + this.age + ' ' + this.branch);
    }

    // Test Case 9 and 12: Update order status to ready to pickup
    protected void setOrderStatus(OrderQueue queue) {
        ArrayList<Order> orders = queue.getOrders();

    }
}
