package branch;

// Branch details
public class Branch implements BranchProvider {
    // Attributes
    public String name;
    public String location;
    public int staffQuota;
    public int managerQuota;

    // Constructor
    public Branch(String name, String location, int staffQuota) {
        this.name = name;
        this.location = location;
        this.staffQuota = staffQuota;
        // TODO: IMPORTANT: ensure in main that (1 <= staffQuota <= 15) as this will affect
        // managerQuota
        if (1 <= staffQuota && staffQuota <= 4) {
            this.managerQuota = 1;
        } else if (5 <= staffQuota && staffQuota <= 8) {
            this.managerQuota = 2;
        } else if (9 <= staffQuota && staffQuota <= 15) {
            this.managerQuota = 3;
        }
    }

    // Getters and setters
    @Override
    public String getBranchName() {
        return this.name;
    }

    public void setBranchName(String name) {
        this.name = name;
    }

    public int getStaffQuota() {
        return this.staffQuota;
    }

    public void setStaffQuota(int quota) {
        this.staffQuota = quota;
    }

    public int getManagerQuota() {
        return this.managerQuota;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() { return this.location; }
}
