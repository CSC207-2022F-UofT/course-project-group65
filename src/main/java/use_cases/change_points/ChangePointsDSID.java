package use_cases.change_points;

import entities.BracketRepo;

public class ChangePointsDSID {

    private BracketRepo updatedBracketRepo;

    public ChangePointsDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepoCP() {
        return this.updatedBracketRepo;
    }

    public void setUpdatedBracketRepoCP(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }


}
