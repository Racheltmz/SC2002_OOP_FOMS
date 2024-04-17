package staff;

import java.util.ArrayList;

import staff.filter.StaffFilterOptions;

public interface IAdminView {
    StaffFilterOptions getFieldToFilter();
}