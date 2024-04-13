package branch;

import payment.PaymentService;
import staff.StaffRoles;
import utils.InputScanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import static authorisation.Authorisation.authoriseAdmin;
import static utils.InputScanner.getInstance;

public class BranchManager {
    private static ServiceLoader<BranchProvider> loader;
    private static final String CONFIG_FILE_PATH = "FOMS/src/branch/META-INF/services/branch.BranchProvider";
    private static List<String> readBranchList() {
        List<String> branches = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                branches.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return branches;
    }

    public static void addBranch(StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH, true))) {
                InputScanner sc = getInstance();
                System.out.println("Enter new branch name: ");
                String newBranchName = sc.next();
                writer.write(newBranchName);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("You are not authorised to perform this action.");
            return;
        }
    }

    public static void closeBranch(StaffRoles auth) {
        if (authoriseAdmin(auth)) {
            List<String> branches = readBranchList();
            System.out.println("Current branches are: ");
            BranchLoader.loadBranches();
            boolean found = false;

            while (!found) {
                InputScanner sc = getInstance();
                System.out.println("Enter name of branch to close: ");
                String branchToRemove = sc.next();

                for (String branch : branches) {
                    if (branch.equalsIgnoreCase(branchToRemove)) {
                        found = true;
                        branches.remove(branchToRemove);
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
                            for (String Branch : branches) {
                                writer.write(Branch);
                                writer.newLine();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("Branch not found. Please try again.");
            }
        }
        else
        {
            System.out.println("You are not authorised to perform this action.");
        }
    }

    public static void displayBranches() {
        {
            BranchLoader.loadBranches();
        }
    }
}
