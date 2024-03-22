package Authentication;

import Management.Company;

public interface StaffPermissions {
    void displayRoleInfo();
    void processMenu(Company company, Authentication auth);
    void logout();
}

