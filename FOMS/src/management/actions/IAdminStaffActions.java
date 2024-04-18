package management.actions;

import management.filter.StaffFilterOptions;

public interface IAdminStaffActions {
    void addStaff();
    void updateStaff();
    void removeStaff();
    void filterStaff(StaffFilterOptions option);
    void promoteStaff();
    void transferStaff();
}