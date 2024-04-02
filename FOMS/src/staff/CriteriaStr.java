package staff;

import java.util.ArrayList;
public interface CriteriaStr {
    ArrayList<Staff> meetCriteria(ArrayList<Staff> staffList, String filterVal);
}