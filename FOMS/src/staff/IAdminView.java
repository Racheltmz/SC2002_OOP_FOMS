package staff;

import java.util.ArrayList;

import staff.filter.StaffFilterOptions;

public interface IAdminView {
    StaffFilterOptions getFieldToFilter();
    void displayAllStaff(ArrayList<Staff> staffDirectory, StaffRoles auth);
}