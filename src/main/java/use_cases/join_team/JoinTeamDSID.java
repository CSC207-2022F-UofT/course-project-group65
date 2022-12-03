package use_cases.join_team;

import entities.*;

public class JoinTeamDSID {
    private BracketRepo updatedBracketRepo;

    public JoinTeamDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    public BracketRepo getUpdatedBracketRepoDSID() {
        return this.updatedBracketRepo;
    }

    public void setUpdatedBracketRepoDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }
}
