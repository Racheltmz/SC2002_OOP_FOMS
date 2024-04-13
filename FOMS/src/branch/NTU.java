package branch;

public class NTU implements BranchProvider {
    @Override
    public String getBranchName() {
        return "NTU";
    }

    @Override
    public int getStaffQuota() {
        return 8;
    }

    @Override
    public int getManagerQuota() {
        if (1 <= this.getStaffQuota() && this.getStaffQuota() <= 4) {
            return 1;
        } else if (5 <= this.getStaffQuota() && this.getStaffQuota() <= 8) {
            return 2;
        } else if (9 <= this.getStaffQuota() && this.getStaffQuota() <= 15) {
            return 3;
        } else {
            return 0;
        }
    }

    @Override
    public String getLocation() {
        return "North spine Plaza";
    }
}
