package authorisation;

import staff.Admin;
import staff.Staff;

// Active factory for admin
public class ActiveFactoryAdmin implements ActiveFactory {
    @Override
    public IActiveUser initInactive() {
        return new ActiveAdmin();
    }
    @Override
    public IActiveUser initActive(Staff staff) {
        return new ActiveAdmin((Admin) staff);
    }
}
