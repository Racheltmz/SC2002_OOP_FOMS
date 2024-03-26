package Management;

import java.util.Scanner;

public class Branch {
    public String name;
    public String location;
    public int staffQuota;
    public int managerQuota;

    public Branch(String name, String location, int staffQuota) {
        this.setBranchName(name);
        this.setLocation(location);
        this.setStaffQuota(staffQuota); // IMPORTANT: ensure in main that (1 <= staffQuota <= 15) as this will affect
                                        // managerQuota
        if (1 <= staffQuota && staffQuota <= 4) {
            this.managerQuota = 1;
        } else if (5 <= staffQuota && staffQuota <= 8) {
            this.managerQuota = 2;
        } else if (9 <= staffQuota && staffQuota <= 15) {
            this.managerQuota = 3;
        }
    }

    public void setBranchName(String name) {
        this.name = name;
    }

    public String getBranchName() {
        return this.name;
    }

    public void setStaffQuota(int quota) {
        this.staffQuota = quota;
    }

    public int getStaffQuota() {
        return this.staffQuota;
    }

    public int getManagerQuota() {
        return this.managerQuota;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
