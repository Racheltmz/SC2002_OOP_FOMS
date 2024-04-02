package staff;

import java.util.ArrayList;
import java.util.Objects;

import static authorisation.Authorisation.authoriseAdmin;

// TODO: IMPLEMENT: FILTERS FOR ALL 4 CRITERIAS
// Records of staff
public class StaffDirectory {
    // Attribute
    private ArrayList<Staff> staffDirectory;

    // Constructor
    public StaffDirectory(ArrayList<Staff> staffDirectory) {
        this.staffDirectory = staffDirectory;
    }

    // Functionalities
    // Get all staff
    public ArrayList<Staff> getStaffDirectory() {
        return this.staffDirectory;
    }

    // Get staff based on staff ID
    public Staff getStaff(String staffId) {
        // Return staff object if it can be found
        for (Staff curStaff : this.staffDirectory) {
            if (Objects.equals(curStaff.getStaffID(), staffId))
                return curStaff;
        }
        // Return null if staff cannot be found
        return null;
    }

    // Add staff (admin purposes)
    public void addStaff(Staff staff, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.staffDirectory.add(staff);
    }

    // Remove staff (admin purposes) (TO CHECK)
    public void rmvStaff(String staffID, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.staffDirectory.remove(this.getStaff(staffID));
    }

    // Filters
    public ArrayList<Staff> filterBranch(String branch) {
        Criteria branchFilter = new CriteriaBranch();
        return branchFilter.getStaffList(this.staffDirectory, branch);
    }

    public ArrayList<Staff> filterRole(String role) {
        Criteria roleFilter = new CriteriaRole();
        return roleFilter.getStaffList(this.staffDirectory, role);
    }

    // Get number of managers to check quota
    public int getNumManagers(StaffRoles auth) {
        if (authoriseAdmin(auth))
            return this.filterRole(StaffRoles.MANAGER.getName()).size();
        return 0;
    }
}
