package ViewTournament;

import entities.BracketRepo;
import use_cases.view_tournament.ViewTournamentDSID;
import use_cases.view_tournament.ViewTournamentGateway;


import java.util.HashMap;

public class ViewTournInMemoryBracket implements ViewTournamentGateway {
    final private HashMap<BracketRepo, ViewTournamentDSID> bracketRepos = new HashMap<>();

    @Override
    public void save(ViewTournamentDSID dsid) throws Exception {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}

