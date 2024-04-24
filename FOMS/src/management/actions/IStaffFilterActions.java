package management.actions;

/**
 * Interface defining Admin's filter actions for staff members.
 */
public interface IStaffFilterActions {

    /**
     * Applies a branch filter to filter staff members by Branch.
     *
     * @param branch The Branch to filter by.
     */
    void applyBranchFilter(String branch);

    /**
     * Applies a StaffRoles filter to filter staff members by StaffRoles.
     *
     * @param role The StaffRoles role to filter by.
     */
    void applyRoleFilter(String role);

    /**
     * Applies a gender filter to filter staff members by gender.
     *
     * @param gender The gender to filter by.
     */
    void applyGenderFilter(String gender);

    /**
     * Applies an age filter to filter staff members by age.
     *
     * @param age The age to filter by.
     */
    void applyAgeFilter(int age);
}
