package staff;

import branch.Branch;
import staff.filter.StaffFilterOptions;

public interface IAdminActions {
    void addStaff(StaffRoles auth);
    void updateStaff(StaffRoles auth);
    void removeStaff(StaffRoles auth);
    void filterStaff(StaffFilterOptions option);
    boolean assignManager(Branch branch, StaffRoles auth);
    void promoteStaff(StaffRoles auth);
    void transferStaff(StaffRoles auth);
    void addBranch(StaffRoles auth);
    void closeBranch(StaffRoles auth);
}

