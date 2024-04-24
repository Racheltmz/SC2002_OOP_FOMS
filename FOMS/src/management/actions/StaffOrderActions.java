package management.actions;

import branch.Branch;
import order.OrderQueue;

/**
 * Provides Staff users Order actions for managing Orders in their Branch.
 */
public class StaffOrderActions implements IStaffOrderActions {

    /**
     * Retrieves and displays all NEW Orders in the OrderQueue of the Branch.
     *
     * @param branch The Branch from which to retrieve existing NEW Orders.
     */
    public void getNewOrders(Branch branch) {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayNewOrders(branch.getName());
    }

    /**
     * Retrieves and displays details of a specific Order in the OrderQueue of the specified Branch.
     *
     * @param branch The Branch from which to retrieve Order details.
     */
    public void getOrderDetails(Branch branch) {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayOrder(branch.getName());
    }

    /**
     * Updates an existing Order's OrderStatus from NEW to READY in the specific Branch.
     *
     * @param branch The Branch in which the Order exists.
     */
    public void setOrderReady(Branch branch) {
        OrderQueue queue = OrderQueue.getInstance();
        queue.updateStatusToReady(branch.getName());
    }
}