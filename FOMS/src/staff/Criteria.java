package staff;

import java.util.ArrayList;

public interface Criteria {
    ArrayList<Staff> getStaffList(ArrayList<Staff> staffList, String filterVal);
}
