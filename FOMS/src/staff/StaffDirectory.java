package staff;

import java.util.ArrayList;
import java.util.Objects;

import exceptions.InvalidInputException;
import exceptions.ItemNotFoundException;

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
    public Staff getStaff(String staffId) throws ItemNotFoundException {
        // Return staff object if it can be found
        for (Staff curStaff : this.staffDirectory) {
            if (Objects.equals(curStaff.getStaffID(), staffId))
                return curStaff;
        }
        // Return null if staff cannot be found
        throw new ItemNotFoundException("Staff cannot be found");
    }

    // Add staff (admin purposes)
    public void addStaff(Staff staff, StaffRoles auth) {
        if (authoriseAdmin(auth))
            this.staffDirectory.add(staff);
    }

    // Remove staff (admin purposes) (TO CHECK)
    public void rmvStaff(String staffID, StaffRoles auth) throws ItemNotFoundException {
        if (authoriseAdmin(auth))
            this.staffDirectory.remove(this.getStaff(staffID));
    }

    // Filters
    public ArrayList<Staff> filterBranch(String branch) {
        StaffList toFilter = new StaffList();
        try {
            return toFilter.getStaffBranch(this.staffDirectory, branch);
        } catch (InvalidInputException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return staffDirectory;
    }

    public ArrayList<Staff> filterRole(StaffRoles role) {
        StaffList toFilter = new StaffList();
        return toFilter.getStaffRole(this.staffDirectory, role);
    }

    // Get number of managers to check quota
    public int getNumManagers(StaffRoles auth) {
        if (authoriseAdmin(auth))
            return this.filterRole(StaffRoles.MANAGER).size();
        return 0;
    }
}
