package staff;

import java.util.ArrayList;

public interface StaffFilter {
    ArrayList<Staff> getStaffList(ArrayList<Staff> staffList, String filterVal);
}
