package org.example;

public class BranchController {

    private  BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    public String getBranchInfo() {
        return branchService.getBranchInfo();
    }

    public void setBranchService(BranchService branchService) {
    }
}
