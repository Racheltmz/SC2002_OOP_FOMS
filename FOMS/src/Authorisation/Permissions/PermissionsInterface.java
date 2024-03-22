package Authorisation.Permissions;

import Authentication.Authentication;
import Management.Company;

public interface PermissionsInterface {
    void displayRoleInfo();
    void processMenu(Company company, Authentication auth);
    void logout();
}

