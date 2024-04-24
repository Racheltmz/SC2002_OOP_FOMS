package management.actions;

/**
 * Interface defining Admin's administrative actions related to branches.
 */
public interface IAdminBranchActions {
    /**
     * Adds a new branch.
     */
    void addBranch();

    /**
     * Closes an existing branch.
     */
    void closeBranch();
}