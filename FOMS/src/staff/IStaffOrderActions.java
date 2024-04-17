package staff;

import branch.Branch;

public interface IStaffOrderActions {
    void getNewOrders(Branch branch);
    void getOrderDetails(Branch branch);
    void setOrderReady(Branch branch);
}
