package management;

/**
 * Enumeration representing the StaffRoles that staff members can have within the FOMS system.
 * Each role has an associated acronym that represents the StaffRole.
 */
public enum StaffRoles {
    STAFF("S"),
    MANAGER("M"),
    ADMIN("A");
    private final String roleName;

    /**
     * Constructs a StaffRoles enum with the given acronym value as its role name.
     *
     * @param value The acronym value representing the role name.
     */
    StaffRoles(String value) {
        roleName = value;
    }

    /**
     * Retrieves the acronym representing the StaffRoles role.
     *
     * @return The acronym representing the StaffRoles role.
     */
    public String getAcronym() {
        return roleName;
    }
}