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

    public void getStaff() 
    {
        System.out.println(this.staffID + ' ' + this.name + ' ' + this.role + ' ' +
                this.gender + ' ' + this.age + ' ' + this.branch + ' ' + this.password);
    }

    public boolean login(Scanner sc) 
    {
        boolean loggedIn = false;
        System.out.print("Enter staffID: ");
        String inputStaffID = sc.nextLine();
        while (!"Quit".equalsIgnoreCase(inputStaffID)) 
        {
            System.out.print("Enter password: ");
            String inputPassword = sc.nextLine().trim();
            if (verifyPassword(inputPassword) && verifyStaffID(inputStaffID)) 
            {
                System.out.println("Logged in!");
                loggedIn = true;
                break;
            } 

            else 
            {
                System.out.println("Incorrect staffID and/or password, please try again.");
                System.out.print("Enter staffID: ");
                inputStaffID = sc.nextLine().trim();
                continue;
            }
        }
        return loggedIn;
    }

    protected boolean verifyPassword(String password) 
    {
        return password.equals(this.password);
    }

    protected boolean verifyStaffID(String staffID)
    {
        return staffID.equals(this.staffID);
    }

    protected void setStatus(String orderStatus)
    {

    }

    public static void main(String[] args) 
    {
        try (Scanner sc = new Scanner(System.in)) 
        {
            System.out.println("Please select option (3 to quit): ");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            int choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                    // Customer interface
                    break;
                case 2:
                    // Staff interface
                    Staff kumar = new Staff("kumarB", "kumar Blackmore", Roles.STAFF, 'M', 32, "NTU");
                    System.out.println("Welcome to the FOMS Login System. Please select option (3 to quit): ");
                    System.out.println("1. Staff login");
                    System.out.println("2. Change password");
                    int option = sc.nextInt();
                    sc.nextLine(); // Consume newline character
                    while (option >= 1) 
                    {
                        if (option == 1) 
                        {
                            kumar.login(sc);
                            break;
                        } 
                        else if (option == 2) 
                        {
                            if (kumar.login(sc)) 
                            {
                                System.out.print("Please enter new password: ");
                                String newpassword = sc.nextLine().trim();
                                while (newpassword.isEmpty()) 
                                {
                                    System.out.println("Password change unsuccessful, please try again.");
                                    System.out.print("Please enter new password: ");
                                    newpassword = sc.nextLine().trim();
                                }
                                kumar.setPassword(newpassword);
                                System.out.println("Password successfully changed!");
                            }
                        } 
                        else if (option == 3)
                        {
                            break;
                        }
                        else if (option != 3) 
                        {
                            System.out.print("Invalid choice, please re-enter: ");
                        }
                        System.out.println("Welcome to the FOMS Login System. Please select option (3 to quit): ");
                        System.out.println("1. Staff login");
                        System.out.println("2. Change password");
                        option = sc.nextInt();
                        sc.nextLine();
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.print("Invalid choice, please re-enter: ");
                    choice = sc.nextInt();
            }
        }
    }
}