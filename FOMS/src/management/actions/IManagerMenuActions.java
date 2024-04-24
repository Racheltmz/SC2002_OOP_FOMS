package management.actions;

import branch.Branch;

/**
 * Interface defining Menu management actions for a Manager.
 */
public interface IManagerMenuActions {
    /**
     * Adds a new MenuItem for the specified Branch.
     *
     * @param branch The Branch to add the MenuItem to.
     */
    void addMenuItem(Branch branch);

    /**
     * Updates details of an existing MenuItem for the specified Branch.
     *
     * @param branch The Branch in which the MenuItem exists.
     */
    void updateMenuItem(Branch branch);

    /**
     * Removes an existing MenuItem from the specified Branch.
     *
     * @param branch The Branch from which to remove the MenuItem.
     */
    void removeMenuItem(Branch branch);
}