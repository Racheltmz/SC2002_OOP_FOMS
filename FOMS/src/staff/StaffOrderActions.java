package staff;

import branch.Branch;
import order.OrderQueue;

public class StaffOrderActions implements IStaffOrderActions {
    public void getNewOrders(Branch branch) {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayNewOrders(branch.getName());
    }

    // Specific order
    public void getOrderDetails(Branch branch) {
        OrderQueue queue = OrderQueue.getInstance();
        queue.displayOrder();
    }

    // Update order status to ready to pickup for Test Case 10 and 12
    public void setOrderReady(Branch branch) {
        OrderQueue queue = OrderQueue.getInstance();
        queue.updateStatusToReady();
    }
}
