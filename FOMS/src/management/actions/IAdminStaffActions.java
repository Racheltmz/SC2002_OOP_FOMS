package management.actions;

import management.filter.StaffFilterOptions;

/**
 * Interface defining Admin's administrative actions related to staff management.
 */
public interface IAdminStaffActions {
    /**
     * Adds a new staff member.
     */
    void addStaff();

    /**
     * Updates details of an existing staff member.
     */
    void updateStaff();

    /**
     * Removes an existing staff member.
     */
    void removeStaff();

    /**
     * Filters staff members based on the specified filter option.
     *
     * @param option The option to filter staff members by.
     */
    void filterStaff(StaffFilterOptions option);

    /**
     * Promotes a Staff to a Manager.
     */
    void promoteStaff();

    /**
     * Transfers a staff member to a different branch if applicable.
     */
    void transferStaff();
}