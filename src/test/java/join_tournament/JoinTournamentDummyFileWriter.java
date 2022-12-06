package join_tournament;

import entities.BracketRepo;
import use_cases.join_tournament.JoinTournamentDSID;
import use_cases.join_tournament.JoinTournamentGateway;

import java.util.HashMap;

public class JoinTournamentDummyFileWriter implements JoinTournamentGateway {
    final private HashMap<BracketRepo, JoinTournamentDSID> bracketRepos = new HashMap<>();

    @Override
    public void save(JoinTournamentDSID dsid) throws Exception {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}
