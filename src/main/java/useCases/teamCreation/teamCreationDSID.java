package useCases.teamCreation;


import entities.*;

public class teamCreationDSID {
    private final BracketRepo updatedBracketRepo;

    public teamCreationDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepoDSID() {
        return this.updatedBracketRepo;
    }

}
