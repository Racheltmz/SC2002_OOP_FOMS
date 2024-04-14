package staff;

import branch.Branch;

public interface IStaffActionsOrder {
    void getNewOrders(Branch branch);
    void getOrderDetails();
    void setOrderReady();
}
