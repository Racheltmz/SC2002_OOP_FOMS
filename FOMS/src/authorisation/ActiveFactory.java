package authorisation;

import staff.Staff;

// Active Factory interface
public interface ActiveFactory {
    ActiveUser initInactive();
    ActiveUser initActive(Staff staff);
}
