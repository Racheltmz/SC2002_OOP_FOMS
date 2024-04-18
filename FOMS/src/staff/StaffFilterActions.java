package staff;

import staff.filter.StaffFilterAge;
import staff.filter.StaffFilterBranch;
import staff.filter.StaffFilterGender;
import staff.filter.StaffFilterRole;
import utils.IFilter;

public class StaffFilterActions {
    private void filterByBranch(String branch) {
        IFilter staffFilterBranch = new StaffFilterBranch();
        staffFilterBranch.filter(branch);
    }

    private void filterByRole(String role) {
        IFilter staffFilterRole = new StaffFilterRole();
        staffFilterRole.filter(role);
    }

    private void filterByGender(String gender) {
        IFilter staffFilterGender = new StaffFilterGender();
        staffFilterGender.filter(gender);
    }

    private void filterByAge(int age) {
        IFilter staffFilterAge = new StaffFilterAge();
        staffFilterAge.filter(String.valueOf(age));
    }

    // Public method to expose functionality in a controlled way
    public void applyBranchFilter(String branch) {
        filterByBranch(branch);
    }

    public void applyRoleFilter(String role) {
        filterByRole(role);
    }

    public void applyGenderFilter(String gender) {
        filterByGender(gender);
    }

    public void applyAgeFilter(int age){
        filterByAge(age);
    }
}