package branch;

import io.IXlsxSerializable;

import java.util.UUID;

/**
 * Represents a branch of the company.
 */
public class Branch implements IXlsxSerializable {
    /** Unique identifier for this branch. */
    private UUID id = UUID.randomUUID();

    /** Name of this branch. */
    public String name;

    /** Location of this branch. */
    public String location;

    /** Maximum number of staff allowed in this branch. */
    public int staffQuota;

    /** Number of managers required in this branch based on staff quota. */
    public int managerQuota;

    /**
     * Constructs a new branch with the given name, location, and staff quota.
     * The name is an acronym, while the location is the full form.
     *
     * @param name          This branch's name.
     * @param location      This branch's location.
     * @param staffQuota    This branch's staffQuota.
     */
    public Branch(String name, String location, int staffQuota) {
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

    /**
     * Constructs a new branch with the given ID, name, location, and staff quota.
     *
     * @param id            This branch's unique identifier.
     * @param name          This branch's name.
     * @param location      This branch's location.
     * @param staffQuota    This branch's staffQuota.
     */
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
        return new String[] { String.valueOf(id), name, location, String.valueOf(staffQuota) };
    }

    /**
     * Gets the unique identifier of this branch.
     * @return this Branch's UUID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the name of this branch.
     * @return this Branch's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this branch.
     * @param name This branch's new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of this branch.
     * @return this Branch's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of this branch.
     * @param location This branch's new location.
     */
    public void setLocation(String location){ this.location = location; }

    /**
     * Gets the staffQuota of this branch.
     * @return this Branch's staffQuota.
     */
    public int getStaffQuota() {
        return this.staffQuota;
    }

    /**
     * Sets the staffQuota of this branch.
     * @param quota This branch's new staffQuota.
     */
    public void setStaffQuota(int quota) {
        this.staffQuota = quota;
    }

    /**
     * Gets the managerQuota of this branch.
     * @return this Branch's managerQuota.
     */
    public int getManagerQuota() {
        return this.managerQuota;
    }

}
