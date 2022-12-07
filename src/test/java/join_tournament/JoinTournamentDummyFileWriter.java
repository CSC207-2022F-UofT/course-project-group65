package join_tournament;

import entities.BracketRepo;
import use_cases.join_tournament.JoinTournamentDSID;
import use_cases.join_tournament.JoinTournamentGateway;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents the joinTournament file writer for the test use
 */
public class JoinTournamentDummyFileWriter implements JoinTournamentGateway {
    /**
     * This hashmap variable is for storing bracketRepos
     */
    final private HashMap<BracketRepo, JoinTournamentDSID> bracketRepos = new HashMap<>();

    /**
     * Write the given data to the file.
     *
     * @param dsid The data to write
     * @throws IOException If an error occurs while writing to the file
     */

    @Override
    public void save(JoinTournamentDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}
