package branch;

public class JE implements BranchProvider {
    @Override
    public String getBranchName() {
        return "JE";
    }

    @Override
    public int getStaffQuota() {
        return 11;
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
        return "Jurong east";
    }
}
