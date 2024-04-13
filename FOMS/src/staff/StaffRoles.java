package staff;

// Staff Roles
public enum StaffRoles {
    STAFF("S"),
    MANAGER("M"),
    ADMIN("A");
    private final String roleName;

    private StaffRoles(String value) {
        roleName = value;
    }

    public String getAcronym() {
        return roleName;
    }
}
