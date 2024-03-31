package Management;

import java.util.Scanner;
import java.util.ArrayList; 
import java.Iterate; 

public class Admin extends Staff {
    private Company company; 

    public Admin(String staffID, String name, Roles role, char gender, int age, String branch) {
        super(staffID, name, role, gender, age, branch);
        this.company = company; 
    }

    public void addBranch(String Branch)
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter branch name: ");
            String name = sc.nextLine();
            System.out.print("Enter staff quota: ");
            int quota = sc.nextInt();
            while (!(1 <= quota && quota <= 15)) {
                System.out.println("Invalid staff quota! Please re-enter.");
                System.out.print("Enter staff quota: ");
                quota = sc.nextInt();
            }
            System.out.print("Enter branch location: ");
            String location = sc.next();
            location += sc.nextLine();
            Branch branch = new Branch(name, location, quota);
            System.out.printf("New branch created. Its details are:\nName: %s\nStaff quota: %d\nManager Quota: %d\nLocation: %s\n", name, quota, branch.getManagerQuota(), location);
        }
    }

    public void closeBranch(String Branch){

    }
}
