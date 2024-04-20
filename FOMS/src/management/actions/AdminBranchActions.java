package management.actions;

import static utils.LetterCaseHelper.toProperCase;
import static utils.ValidateHelper.validateIntRange;

import java.util.InputMismatchException;

import branch.Branch;
import branch.BranchDirectory;
import utils.InputScanner;

public class AdminBranchActions implements IAdminBranchActions {
    InputScanner sc = InputScanner.getInstance();
    BranchDirectory branchDirectory = BranchDirectory.getInstance();

    public void addBranch() {
        try {
            // Get branch name
            System.out.print("Enter branch name: ");
            String name = sc.nextLine();
            // Get location
            System.out.print("Enter branch location: ");
            String location = sc.nextLine();
            // Get staff quota
            int quota = validateIntRange("Enter staff quota: ", 1, 15);
            Branch newBranch = new Branch(name.toUpperCase(), toProperCase(location), quota);
            branchDirectory.addBranch(newBranch);
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateBranch() {
        try{
            System.out.print("Enter branch name to update: ");
            String branchName = sc.nextLine();
            Branch branchToUpdate = branchDirectory.getBranchByName(branchName);

            if(branchToUpdate != null){
                System.out.print("Enter new branch name (leave blank to keep current name): ");
                String newName = sc.nextLine();
                if(!newName.isEmpty()){
                    branchToUpdate.setName(newName);
                }

                System.out.print("Enter new branch location (leave blank to keep current location): ");
                String newLocation = sc.nextLine();
                if(!newLocation.isEmpty()){
                    branchToUpdate.setLocation(newLocation);
                }

                // TODO: check range
                System.out.print("Enter new staff quota (leave blank to keep current quota): ");
                String quotaInput = sc.nextLine();
                if(!quotaInput.isEmpty()) {
                    try {
                        int newQuota = Integer.parseInt(quotaInput);
                        branchToUpdate.setStaffQuota(newQuota);
                    } catch (NumberFormatException e){
                        System.out.print("Invalid number format for quota. Please enter a valid integer.");
                    }
                }

                branchDirectory.updateBranch(branchToUpdate);
                System.out.println("Branch updated successfully.");
            }
            else{
                System.out.print("Branch not found");
            }
        }
        catch(InputMismatchException | NumberFormatException e){
            System.out.print("Invalid input. Please try again.");
        }
    }

    public void closeBranch() {
        // Get branch by name
        Branch branchToRmv = branchDirectory.getBranchByUserInput();
        branchDirectory.rmvBranch(branchToRmv);
    }
}