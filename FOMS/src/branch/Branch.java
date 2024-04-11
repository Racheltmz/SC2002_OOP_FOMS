package branch;

import utils.IXlsxSerializable;

// Branch details
public class Branch implements IXlsxSerializable {
    // Attributes
//    private static final long serialVersionUID = 1L;
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
        if (staffQuota >= 1 && staffQuota <= 4) {
            this.managerQuota = 1;
        } else if (staffQuota >= 5 && staffQuota <= 8) {
            this.managerQuota = 2;
        } else if (staffQuota >= 9 && staffQuota <= 15) {
            this.managerQuota = 3;
        }
    }

    // Serialization to XLSX
    public String[] toXlsx() {
        return new String[] { name, location, String.valueOf(staffQuota), String.valueOf(managerQuota)};
//        return new String[] {String.valueOf(serialVersionUID), name, location, String.valueOf(staffQuota), String.valueOf(managerQuota)};
    }

    // Getters and setters
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
}
