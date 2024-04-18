package management.actions;

public interface IStaffFilterActions {
    void applyBranchFilter(String branch);
    void applyRoleFilter(String role);
    void applyGenderFilter(String gender);
    void applyAgeFilter(int age);
}
