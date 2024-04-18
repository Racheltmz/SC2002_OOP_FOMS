package management.actions;

import management.filter.StaffFilterAge;
import management.filter.StaffFilterBranch;
import management.filter.StaffFilterGender;
import management.filter.StaffFilterRole;
import utils.IFilter;

public class StaffFilterActions implements IStaffFilterActions {
    public void applyBranchFilter(String branch) {
        IFilter staffFilterBranch = new StaffFilterBranch();
        staffFilterBranch.filter(branch);
    }

    public void applyRoleFilter(String role) {
        IFilter staffFilterRole = new StaffFilterRole();
        staffFilterRole.filter(role);
    }

    public void applyGenderFilter(String gender) {
        IFilter staffFilterGender = new StaffFilterGender();
        staffFilterGender.filter(gender);
    }

    public void applyAgeFilter(int age) {
        IFilter staffFilterAge = new StaffFilterAge();
        staffFilterAge.filter(String.valueOf(age));
    }
}