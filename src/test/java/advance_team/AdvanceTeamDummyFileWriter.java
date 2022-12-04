package advance_team;

import entities.BracketRepo;
import use_cases.advance_team.AdvanceTeamDSID;
import use_cases.advance_team.AdvanceTeamGateway;
import use_cases.view_tournament.ViewTournamentDSID;

import java.io.IOException;
import java.util.HashMap;

public class AdvanceTeamDummyFileWriter implements AdvanceTeamGateway {

    final private HashMap<BracketRepo, AdvanceTeamDSID> bracketRepos = new HashMap<>();

    @Override
    public void save(AdvanceTeamDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getUpdatedBracketRepo());
        bracketRepos.put(dsid.getUpdatedBracketRepo(), dsid);
    }
}
