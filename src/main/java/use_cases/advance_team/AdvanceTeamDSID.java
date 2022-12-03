package use_cases.advance_team;
import entities.*;

public class AdvanceTeamDSID {

    private BracketRepo updatedBracketRepo;

    public AdvanceTeamDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepo() {
        return this.updatedBracketRepo;
    }

    public void setUpdatedBracketRepo(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }
}
