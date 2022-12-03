package JoinTournament;

import entities.BracketRepo;
import useCases.joinTournament.JoinTournamentDSID;
import useCases.joinTournament.JoinTournamentGateway;

import java.util.HashMap;

public class JoinTournInMemoryBracket implements JoinTournamentGateway {
    final private HashMap<BracketRepo, JoinTournamentDSID> bracketRepos = new HashMap();

    @Override
    public void save(JoinTournamentDSID dsid) throws Exception {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}
