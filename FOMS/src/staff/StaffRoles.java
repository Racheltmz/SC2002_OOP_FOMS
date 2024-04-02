package staff;

// Staff Roles
public enum StaffRoles {
    STAFF("Staff"),
    MANAGER("Manager"),
    ADMIN("Admin");
    private final String roleName;

    private StaffRoles(String value) {
        roleName = value;
    }

    public String getName() {
        return roleName;
    }
}
