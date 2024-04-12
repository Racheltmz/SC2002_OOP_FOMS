package branch;

import utils.IXlsxSerializable;

import java.util.UUID;

// Branch details
public class Branch implements IXlsxSerializable {
    // Attributes
    private UUID id = UUID.randomUUID();
    public String name;
    public String location;
    public int staffQuota;
    public int managerQuota;

    // Constructor
    public Branch(String name, String location, int staffQuota) {
        this.name = name;
        this.location = location;
        this.staffQuota = staffQuota;
        // TODO: NEW ensure in main that (1 <= staffQuota <= 15) as this will affect
        if (staffQuota >= 1 && staffQuota <= 4) {
            this.managerQuota = 1;
        } else if (staffQuota >= 5 && staffQuota <= 8) {
            this.managerQuota = 2;
        } else if (staffQuota >= 9 && staffQuota <= 15) {
            this.managerQuota = 3;
        }
    }

    public Branch(UUID id, String name, String location, int staffQuota) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.staffQuota = staffQuota;
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
        return new String[] { String.valueOf(id), name, location, String.valueOf(staffQuota), String.valueOf(managerQuota)};
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
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

}
