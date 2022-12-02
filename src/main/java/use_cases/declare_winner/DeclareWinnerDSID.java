package use_cases.declare_winner;

import entities.BracketRepo;

public class DeclareWinnerDSID {

    private BracketRepo updatedBracketRepo;

    public DeclareWinnerDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepoDSID() {
        return this.updatedBracketRepo;
    }

    public void setUpdatedBracketRepoDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

}
