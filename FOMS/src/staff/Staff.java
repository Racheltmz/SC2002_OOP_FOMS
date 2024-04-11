package staff;

import order.OrderQueue;
import order.OrderStatus;
import utils.IXlsxSerializable;

import static authentication.Authentication.setNewPassword;
import static authentication.Hashing.genHash;

import exceptions.EmptyListException;

import java.io.Serializable;

// TODO: SOLID: SEPARATE PERMISSIONS INTO A STAFFACTIONS CLASS (OTHER THAN PASSWORD) + CREATE 1 INTERFACE FOR THE ORDERS
// Staff Details
public class Staff implements Serializable, IXlsxSerializable {
//    private static final long serialVersionUID = 1L;
    private String staffID;
    private String name;
    private String password;
    private StaffRoles role;
    private char gender;
    private int age;
    private String branch;

    public Staff(String staffID, String name, StaffRoles role, char gender, int age, String branch) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.password = genHash("password");
    }

    // TODO: CHECK IF CAN BE DONE IN STRING
    // Serialization to XLSX
    public String[] toXlsx() {
        return new String[] { name, staffID, role.toString(), String.valueOf(gender), String.valueOf(age), branch };
//        return new String[] { String.valueOf(serialVersionUID), name, staffID, role.toString(), String.valueOf(gender), String.valueOf(age), branch };
    }

    /* GETTERS AND SETTERS */
    public String getStaffID() {
        return this.staffID;
    }

    public StaffRoles getRole() {
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
    public void getNewOrders(String branchName) throws EmptyListException {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayNewOrders(branchName);
    }

    // Specific order
    public void getOrderDetails() throws EmptyListException {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayOrder();
    }

    // Update order status to ready to pickup for Test Case 10 and 12
    public void setOrderReady() throws EmptyListException {
        OrderQueue queue = OrderQueue.getInstance();
        queue.updateStatus(OrderStatus.NEW, OrderStatus.READY);
    }
}
