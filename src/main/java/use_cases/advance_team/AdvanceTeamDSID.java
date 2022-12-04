package use_cases.advance_team;

import entities.BracketRepo;

public class AdvanceTeamDSID {

    private final BracketRepo updatedBracketRepo;

    public AdvanceTeamDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepo() {
        return this.updatedBracketRepo;
    }

}
