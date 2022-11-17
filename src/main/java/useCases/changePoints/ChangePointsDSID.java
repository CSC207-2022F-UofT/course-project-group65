package useCases.changePoints;

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
