package management;

import branch.Branch;
import branch.BranchDirectory;
import menu.Menu;
import menu.MenuDirectory;
import payment.Payment;
import payment.PaymentDirectory;
import staff.Staff;
import staff.StaffDirectory;

import java.util.ArrayList;

// Company Details
public class Company {
    // Attributes
    private StaffDirectory staffDirectory;
    private BranchDirectory branchDirectory;
    private PaymentDirectory paymentDirectory;
    private MenuDirectory menuDirectory;

    // Constructor
    public Company(ArrayList<Staff> staffList, ArrayList<Branch> branchList, ArrayList<Payment> paymentList, ArrayList<Menu> menuList) {
        this.staffDirectory = new StaffDirectory(staffList);
        this.branchDirectory = new BranchDirectory(branchList);
        this.paymentDirectory = new PaymentDirectory(paymentList);
        this.menuDirectory = new MenuDirectory(menuList);
    }

    // Getters
    public StaffDirectory getStaffDirectory() {
        return staffDirectory;
    }

    public BranchDirectory getBranchDirectory() {
        return branchDirectory;
    }

    public PaymentDirectory getPaymentDirectory() {
        return paymentDirectory;
    }

    public MenuDirectory getMenuDirectory() {
        return menuDirectory;
    }
}
