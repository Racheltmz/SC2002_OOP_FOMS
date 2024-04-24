package management.actions;

import branch.Branch;

/**
 * Interface defining Order actions for Staff users.
 */
public interface IStaffOrderActions {
    /**
     * Retrieves Orders with the NEW OrderStatus for a specific Branch.
     *
     * @param branch The Branch from which to retrieve new Orders.
     */
    void getNewOrders(Branch branch);

    /**
     * Retrieves details of a specific Order for a Branch.
     *
     * @param branch The branch from which to retrieve Order details.
     */
    void getOrderDetails(Branch branch);

    /**
     * Sets an Order as OrderStatus READY for pickup or delivery in a specific Branch.
     *
     * @param branch The Branch in which to set an Order as READY.
     */
    void setOrderReady(Branch branch);
}