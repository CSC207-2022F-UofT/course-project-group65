package view_tournament;

import entities.BracketRepo;
import use_cases.view_tournament.ViewTournamentDSID;
import use_cases.view_tournament.ViewTournamentGateway;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents the viewTournament file writer for the test use
 */
public class ViewTournamentDummyFileWriter implements ViewTournamentGateway {
    /**
     * This hashmap variable is for storing bracketRepos
     */
    final private HashMap<BracketRepo, ViewTournamentDSID> bracketRepos = new HashMap<>();

    /**
     * Write the given data to the file.
     *
     * @param dsid The data to write
     * @throws IOException If an error occurs while writing to the file
     */

    @Override
    public void save(ViewTournamentDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}

