package viewTournament;

import entities.BracketRepo;
import useCases.viewTournament.ViewTournamentDSID;
import useCases.viewTournament.ViewTournamentGateway;

import java.util.HashMap;

public class ViewTournInMemoryBracket implements ViewTournamentGateway {
    final private HashMap<BracketRepo, ViewTournamentDSID> bracketRepos = new HashMap();

    @Override
    public void save(ViewTournamentDSID dsid) throws Exception {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}

