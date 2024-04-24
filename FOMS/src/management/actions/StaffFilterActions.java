package management.actions;

import management.filter.StaffFilterAge;
import management.filter.StaffFilterBranch;
import management.filter.StaffFilterGender;
import management.filter.StaffFilterRole;
import utils.IFilter;

/**
 * Provides Admin users administrative actions for filtering staff based on various criteria.
 */
public class StaffFilterActions implements IStaffFilterActions {
    /**
     * Filters staff by the Branch they are in.
     *
     * @param branch The Branch to filter by.
     */
    public void applyBranchFilter(String branch) {
        IFilter staffFilterBranch = new StaffFilterBranch();
        staffFilterBranch.filter(branch);
    }

    /**
     * Filters staff by their StaffRoles.
     *
     * @param role The StaffRole to filter by.
     */
    public void applyRoleFilter(String role) {
        IFilter staffFilterRole = new StaffFilterRole();
        staffFilterRole.filter(role);
    }

    /**
     * Filters staff by their gender.
     *
     * @param gender The gender to filter by.
     */
    public void applyGenderFilter(String gender) {
        IFilter staffFilterGender = new StaffFilterGender();
        staffFilterGender.filter(gender);
    }

    /**
     * Filters staff by their age.
     *
     * @param age The StaffRole to filter by.
     */
    public void applyAgeFilter(int age) {
        IFilter staffFilterAge = new StaffFilterAge();
        staffFilterAge.filter(String.valueOf(age));
    }
}