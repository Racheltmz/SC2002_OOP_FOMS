package authorisation;

import staff.Manager;
import staff.Staff;

// Active factory for manager
public class ActiveFactoryManager implements ActiveFactory {
    @Override
    public IActiveUser initInactive() {
        return new ActiveManager();
    }
    @Override
    public IActiveUser initActive(Staff staff) {
        return new ActiveManager((Manager) staff);
    }
}
