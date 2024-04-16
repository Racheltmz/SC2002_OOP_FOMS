package staff;

import branch.Branch;

public interface IStaffActionsOrder {
    void getNewOrders(Branch branch);
    void getOrderDetails(Branch branch);
    void setOrderReady(Branch branch);
}
