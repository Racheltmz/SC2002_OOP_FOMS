package staff.actions;

import staff.filter.StaffFilterAge;
import staff.filter.StaffFilterBranch;
import staff.filter.StaffFilterGender;
import staff.filter.StaffFilterRole;
import utils.IFilter;

public class StaffFilterActions {
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