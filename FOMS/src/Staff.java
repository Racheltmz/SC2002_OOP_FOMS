public class Staff {
    private String staffID;
    private String name;
    private char role;
    private char gender;
    private int age;
    private String branch;
    private String password;
    public Staff(String staffID, String name, char role, char gender, int age, String branch) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
        this.password = "password";
    }
    public void getStaff() {
        System.out.println(this.staffID + ' ' + this.name + ' ' + this.role + ' ' +
                this.gender + ' ' + this.age + ' ' + this.branch + ' ' + this.password);
    }
}
