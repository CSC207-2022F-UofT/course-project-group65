package useCases.teamCreation;


import entities.*;

/**
 * This is a class for accessing data from the Database.
 */

public class teamCreationDSID {
    private final BracketRepo updatedBracketRepo;

    public teamCreationDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepoDSID() {
        return this.updatedBracketRepo;
    }

}
