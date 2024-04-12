package authorisation;

import staff.StaffRoles; 

// Interface for authorisation based on staff's role 
public interface AuthorisationInterface {
    boolean authoriseManager(StaffRoles role); 

    boolean authoriseAdmin(StaffRoles role); 

    boolean authoriseCustomRole(StaffRoles expectedRole, StaffRoles roleToCheck); 
}
