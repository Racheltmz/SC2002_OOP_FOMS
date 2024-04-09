package staff;

import order.OrderQueue;
import order.OrderStatus;
import utils.IXlsxSerializable;

import static authentication.Authentication.setNewPassword;
import static authentication.Hashing.genHash;

import exceptions.EmptyListException;
import exceptions.IllegalArgumentException;

// TODO: SOLID: SEPARATE PERMISSIONS INTO A STAFFACTIONS CLASS (OTHER THAN PASSWORD) + CREATE 1 INTERFACE FOR THE ORDERS
// Staff Details
public class Staff implements IXlsxSerializable {
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
    
  // Constructor for deserialization from XLSX data
  public Staff(String[] data) throws IllegalArgumentException {
    if (data.length != 6) {
        throw new IllegalArgumentException("Invalid data format for Staff");
    }
    this.name = data[0];
    this.staffID = data[1];
    this.role = StaffRoles.valueOf(data[2]); // Assuming role is stored as a String representation of the enum
    this.gender = data[3].charAt(0); // Assuming gender is stored as a single character
    this.age = Integer.parseInt(data[4]);
    this.branch = data[5];
}

       // Serialization to XLSX
       public String[] toXlsx() {
        return new String[] {name, staffID, role.toString(), String.valueOf(gender), String.valueOf(age), branch};
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
    public void getNewOrders(String branchName, OrderQueue queue) throws EmptyListException {
        queue.displayNewOrders(branchName);
    }

    // Specific order
    public void getOrderDetails(OrderQueue queue) throws EmptyListException{
        queue.displayOrder();
    }

    // Update order status to ready to pickup for Test Case 10 and 12
    public void setOrderReady(OrderQueue queue) throws EmptyListException {
        queue.updateStatus(OrderStatus.NEW, OrderStatus.READY);
    }
}
