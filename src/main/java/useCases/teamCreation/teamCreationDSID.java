package useCases.teamCreation;


import entities.*;

public class teamCreationDSID {
    private BracketRepo updatedBracketRepo;

    public teamCreationDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepoDSID() {
        return this.updatedBracketRepo;
    }

    public void setUpdatedBracketRepoDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }
}
