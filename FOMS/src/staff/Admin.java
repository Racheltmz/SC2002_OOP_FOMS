package staff;

import java.util.UUID;

public class Admin extends Staff {
    public Admin(UUID id, String staffID, String name, StaffRoles role, char gender, int age) {
        super(id, staffID, name, role, gender, age, null);
    }
}
