package management;

import branch.Branch;
import io.IXlsxSerializable;
import management.actions.IStaffOrderActions;
import management.actions.StaffOrderActions;
import utils.InputScanner;

import static authentication.Hashing.genHash;
import static utils.ValidateHelper.validateString;

import java.util.Objects;
import java.util.UUID;

/**
 * Staff class represents a Staff member and provides methods for administrative actions
 * to manage Orders within their own Branch.
 */
public class Staff implements IXlsxSerializable {
    private UUID id = UUID.randomUUID();
    private String staffID;
    private String name;
    private StaffRoles role;
    private final char gender;
    private int age;
    private Branch branch;
    private String password;
    private final IStaffOrderActions staffOrderActions = new StaffOrderActions();

    /**
     * Constructs a Staff
     *
     * @param staffID Staff staffID
     * @param name Name of the Staff
     * @param role StaffRole of the Staff
     * @param gender Gender of the Staff
     * @param age Age of the Staff
     * @param branch Branch of the Staff
     */
    public Staff(String staffID, String name, StaffRoles role, char gender, int age, Branch branch) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.password = genHash("password");
    }

    /**
     * Constructs a Staff
     *
     * @param id Record ID
     * @param staffID Staff staffID
     * @param name Name of the Staff
     * @param role StaffRole of the Staff
     * @param gender Gender of the Staff
     * @param age Age of the Staff
     * @param branch Branch of the Staff
     * @param password Password of the Staff
     */
    public Staff(UUID id, String staffID, String name, StaffRoles role, char gender, int age, Branch branch, String password) {
        this.id = id;
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.password = password;
    }

    /**
     * Serialization to XLSX for Staff and Manager.
     * @return String array of the Staff record.
     */
    public String[] toXlsx() {
        String branchName = null;
        if (!Objects.equals(role.getAcronym(), "A"))
            branchName = branch.getName();
        return new String[] {
                String.valueOf(id), name, staffID, role.getAcronym(), String.valueOf(gender),
                String.valueOf(age), branchName, password };
    }

    /**
     * Get record ID of this Staff.
     *
     * @return Record ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get staffID of this Staff.
     *
     * @return The staffID.
     */
    public String getStaffID() {
        return this.staffID;
    }

    /**
     * Sets the staffID of this Staff.
     *
     * @param staffID The staffID to be set.
     */
    public void setStaffID(String staffID) { this.staffID = staffID; }

    /**
     * Get the name of this Staff.
     *
     * @return This Staff's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get StaffRole role of this Staff.
     *
     * @return This Staff's role.
     */
    public StaffRoles getRole() {
        return this.role;
    }

    /**
     * Sets the StaffRoles role of this Staff.
     *
     * @param role The role to be set.
     */
    public void setRole(StaffRoles role) {
        this.role = role;
    }

    /**
     * Get the gender of this Staff.
     *
     * @return This Staff's gender.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Get age of this Staff.
     *
     * @return This Staff's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set age of this Staff.
     *
     * @param age The age to be set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the Branch of this Staff.
     *
     * @return This Staff's Branch.
     */
    public Branch getBranch() {
        return this.branch;
    }

    /**
     * Set Branch of this Staff.
     *
     * @param branch The branch to be set.
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    /**
     * Get the password of this Staff.
     *
     * @return This Staff's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the name of this Staff.
     *
     * @param name The name to be set.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the password of this Staff.
     *
     * @param password The password to be set.
     */
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

    /* MANAGING STAFF PURPOSES */

    /**
     * Changes password after verifying it is not a duplicate or null.
     */
    public void changePassword() {
        InputScanner sc = InputScanner.getInstance();
        System.out.print("\nPlease enter new password: ");
        String newPassword = sc.next();
        // Check if user entered a password or if they entered the same password as they had in the past
        while (newPassword.isEmpty() || this.verifyPassword(genHash(newPassword))) {
            System.out.println("Password change unsuccessful, please try again. Ensure that it differs from your previous password.");
            newPassword = validateString("Please enter new password: ");
        }
        this.setPassword(genHash(newPassword));
        StaffDirectory staffDirectory = StaffDirectory.getInstance();
        staffDirectory.updateStaff(staffDirectory.getStaffById(this.staffID));
        System.out.println("Password successfully changed!");
    }

    /* MANAGING ORDER PURPOSES */

    /**
     * Gets the new Orders in the Branch this Staff is in.
     *
     * @param branch This Staff's branch.
     */
    public void getNewOrders(Branch branch) {
        staffOrderActions.getNewOrders(branch);
    }

    /**
     * Prints the details of the Orders in the Branch this Staff is in.
     *
     * @param branch This Staff's branch.
     */
    public void getOrderDetails(Branch branch) {
        staffOrderActions.getOrderDetails(branch);
    }

    /**
     * Sets the OrderStatus of an Order in this Staff's Branch to ready.
     *
     * @param branch This Staff's branch.
     */
    public void setOrderReady(Branch branch) {
        staffOrderActions.setOrderReady(branch);
    }

}