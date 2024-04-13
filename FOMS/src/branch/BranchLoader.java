//package branch;
//
//import payment.PaymentService;
//
//import java.util.ServiceLoader;
//
//public class BranchLoader {
//    public static void loadBranches() {
//        ServiceLoader<BranchProvider> loader = ServiceLoader.load(BranchProvider.class);
//        for (BranchProvider branch : loader) {
//            System.out.println("Branch name: " + branch.getBranchName());
//            System.out.println("Branch staff quota: " + branch.getStaffQuota());
//            System.out.println("Branch manager quota: " + branch.getManagerQuota());
//            System.out.println("Branch location: " + branch.getLocation());
//        }
//    }
//}
