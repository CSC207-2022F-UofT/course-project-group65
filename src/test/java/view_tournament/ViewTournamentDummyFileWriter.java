package view_tournament;

import entities.BracketRepo;
import use_cases.view_tournament.ViewTournamentDSID;
import use_cases.view_tournament.ViewTournamentGateway;


import java.io.IOException;
import java.util.HashMap;

public class ViewTournamentDummyFileWriter implements ViewTournamentGateway {
    final private HashMap<BracketRepo, ViewTournamentDSID> bracketRepos = new HashMap<>();

    @Override
    public void save(ViewTournamentDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}

