package staff;

import branch.Branch;

public interface IManagerActionsMenu {
    void addMenuItem(Branch branch);
    void updateMenuItem(Branch branch);
    void removeMenuItem(Branch branch);
}
