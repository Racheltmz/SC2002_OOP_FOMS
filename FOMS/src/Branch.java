import java.util.Scanner;

public class Branch 
{
    public String name;
    public String location;
    public int staffQuota;
    public int managerQuota;

    public Branch(String name, int staffQuota, String location)
    {
        this.setBranchName(name);
        this.setLocation(location);
        this.setStaffQuota(staffQuota); //IMPORTANT: ensure in main that (1 <= staffQuota <= 15) as this will affect managerQuota
        if (1 <= staffQuota && staffQuota <= 4)
        {
            this.managerQuota = 1;
        }
        else if (5 <= staffQuota && staffQuota <= 8)
        {
            this.managerQuota = 2;
        }
        else if (9 <= staffQuota && staffQuota <= 15)
        {
            this.managerQuota = 3;
        }
    }

    public void setBranchName(String name)
    {
        this.name = name;
    }

    public String getBranchName()
    {
        return this.name;
    }

    public void setStaffQuota(int quota)
    {
        this.staffQuota = quota;
    }

    public int getStaffQuota()
    {
        return this.staffQuota;
    }

    public int getManagerQuota()
    {
        return this.managerQuota;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public static void main(String[] args) 
    {
        //example of how creating branch works
        try (Scanner sc = new Scanner(System.in))
        {
            System.out.print("Enter branch name: ");
            String name = sc.nextLine();
            System.out.print("Enter staff quota: ");
            int quota = sc.nextInt();
            while (!(1 <= quota && quota <= 15))
            {
                System.out.println("Invalid staff quota! Please re-enter.");
                System.out.print("Enter staff quota: ");
                quota = sc.nextInt();
            }
            System.out.print("Enter branch location: ");
            String location = sc.next();
            location += sc.nextLine();
            Branch branch = new Branch(name, quota, location);
            System.out.printf("New branch created. Its details are:\nName: %s\nStaff quota: %d\nManager Quota: %d\nLocation: %s\n", name, quota, branch.getManagerQuota(), location);
        }
    }
}
