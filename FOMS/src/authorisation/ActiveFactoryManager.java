package authorisation;

import staff.Manager;
import staff.Staff;

// Active factory for manager
public class ActiveFactoryManager implements ActiveFactory {
    @Override
    public ActiveUser initInactive() {
        return new ActiveManager();
    }
    @Override
    public ActiveUser initActive(Staff staff) {
        return new ActiveManager((Manager) staff);
    }
}
