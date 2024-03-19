import java.util.Scanner;
public class Staff {
    public enum Roles { STAFF, MANAGER, ADMIN }
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
        this.password = "password";
    }

    public String getStaffID()
    {
        return this.staffID;
    }

    public void setStaffID(String staffID)
    {
        this.staffID = staffID;
    }

    public Roles getRole()
    {
        return this.role;
    }

    public void setRole(Roles role)
    {
        this.role = role;
    }

    public String getBranch()
    {
        return this.branch;
    }

    public void setBranch(String branch)
    {
        this.branch = branch;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void getStaff() {
        System.out.println(this.staffID + ' ' + this.name + ' ' + this.role + ' ' +
                this.gender + ' ' + this.age + ' ' + this.branch + ' ' + this.password);
    }
    public boolean login()
    {
        try (Scanner sc = new Scanner(System.in))
        {
            boolean x = false;
            System.out.println("Welcome to the FOMS Login system. Please enter 'Quit' under staffID to quit.");
            System.out.println("Enter staffID: ");
            String inputStaffID = sc.nextLine();
            if (inputStaffID != "Quit")
            {
                System.out.println("Enter password: ");
                String inputPassword = sc.nextLine();
                if (verifyPassword(inputPassword) && verifyStaffID(inputStaffID))
                {
                    System.out.println("Logged in!");
                    return true;
                }
                else if (verifyStaffID(staffID) && (!(verifyPassword(password))))
                {
                    System.out.println("Incorrect password, please try again.");
                }
                else if (!verifyStaffID(staffID) && verifyPassword(password))
                {
                    System.out.println("Incorrect staffID, please try again.");
                }
                else
                {
                    System.out.println("Incorrect staffID and password, please try again.");
                }
                x = false;
            }
            return x;
        }
    }

    protected boolean verifyPassword(String password) 
    {
        return password.equals(this.password);
    }

    protected boolean verifyStaffID(String staffID)
    {
        return staffID.equals(this.staffID);
    }

    protected void changePassword() 
    {
        try (Scanner sc = new Scanner(System.in))
        {
            if (login())
            {
                System.out.println("Please enter new password: ");
                String newpassword = sc.nextLine();
                this.setPassword(newpassword);
                System.out.println("Password successfully changed!");
            }
        }
    }

    protected void setStatus(String orderStatus)
    {

    }
}
