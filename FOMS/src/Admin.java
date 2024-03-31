import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.List; 

public class Admin extends Staff{
    private List<Staff> staffList;
    private List<String> branches; 
    private List<String> paymentMethods; 
    private int staffQuota; 


    public Admin(String staffID, String name, Roles role, char gender, int age, String branch){
        super(staffID, name, role, gender, age, branch); 
        staffList = new ArrayList<>();
        this.branches = new ArrayList<>();
        this.paymentMethods = new ArrayList<>(); 

        staffList.add(new Staff("KumarB", "Kumar Blackmore", Roles.STAFF, 'M', 32, "NTU"));
        staffList.add(new Staff("Alexei", "Alexei", Roles.MANAGER, 'M', 25, "NTU"));
        staffList.add(new Staff("TomC", "Tom Chan", Roles.MANAGER, 'M', 56, "JP"));
        staffList.add(new Staff("AliciaA", "Alicia Ang", Roles.MANAGER, 'F', 27, "JE"));
        staffList.add(new Staff("MaryL", "Mary Lee", Roles.STAFF, 'F', 44, "JE"));
        staffList.add(new Staff("JustinL", "Justin Loh", Roles.STAFF, 'M', 49, "JP"));
        staffList.add(new Staff("boss", "Boss", Roles.ADMIN, 'F', 62, "NTU"));
    }

    public void setRole(String staffID, String branch){

    }

    public void setBranch(String staffID, String branch){

    }

    protected void addBranch(String Branch){
        Scanner scanner = new Scanner(System.in); 

        System.out.println("Enter the Branch Name: \n");
        Branch = scanner.nextLine();
    }

    protected void closeBranch(String Branch){
        Scanner scanner = new Scanner(System.in); 

        System.out.println("Enter the Branch Name: \n"); 
        Branch = scanner.nextLine(); 
    }

    protected void displayStaffList(String filter){
        switch(filter.toLowerCase()){
            case "role":
                System.out.println("Staff List filtered by role (S, M, A): \n");
                for(Staff staff : staffList){
                    System.out.println(staff.getName() + " - Role: " + staff.getRole());
                } 
                break;
            case "gender":
                System.out.println("Staff List filtered by gender: \n");
                for(Staff staff : staffList){
                    System.out.println(staff.getName() + " - Gender: " + staff.getGender());
                }
                break; 
            case "age":
                System.out.println("Staff List filtered by age: \n");
                for(Staff staff : staffList){
                    System.out.println(staff.getName() + " - Age : " + staff.getAge());
                }
            case "branch":
            default:
                System.out.println("Staff List filtered by branch: \n");
                for(Staff staff : staffList){
                    System.out.println(staff.getName() + " - Branch: " + staff.getBranch());
                }
                break; 
        }
    }

    protected void assignManager(String staffId, String Branch){
        //int numManagers = getNumManagers(branch); 

        int quota; 
        if(9 <= numStaff <= 15){
            quota = 3;
        }
        else if(5 <= numStaff <= 8){
            quota = 2
        }
        else if(1 <= numStaff <= 4){
            quota = 1; 
        }

        if(numManagers < quota){
            System.out.println("Manager is assigned to the Branch: " + branch);
        }
        else{
            System.out.println("Cannot assign any more Managers. Quota reached for Branch. \n");
        }
    }

    protected void promoteStaff(String staffID, String Branch){
        Scanner scanner = new Scanner(System.in); 

        System.out.println("Enter the staff ID: \n");
        staffID = scanner.nextLine();

        System.out.println("Enter the Branch Name: \n");
        Branch = scanner.nextLine(); 
    }

    protected void transferStaff(String staffID, String Branch){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the staff ID: \n");
        staffID = scanner.nextLine();

        System.out.println("Enter the Branch Name: \n");
        Branch = scanner.nextLine(); 
    }

    protected String addPayment(String method){

    }

    protected String removePayment(String method){

    }
}

