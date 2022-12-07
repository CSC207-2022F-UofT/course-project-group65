package declare_winner;

import entities.BracketRepo;
import use_cases.declare_winner.DeclareWinnerDSID;
import use_cases.declare_winner.DeclareWinnerGateway;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents the declareWinner file writer for the test use
 */
public class DeclareWinnerDummyFileWriter implements DeclareWinnerGateway {
    /**
     * This hashmap variable is for storing bracketRepos
     */
    final private HashMap<BracketRepo, DeclareWinnerDSID> bracketRepos = new HashMap<>();

    /**
     * Write the given data to the file.
     *
     * @param dsid The data to write
     * @throws IOException If an error occurs while writing to the file
     */
    @Override
    public void save(DeclareWinnerDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getUpdatedBracketRepoDSID());
        bracketRepos.put(dsid.getUpdatedBracketRepoDSID(), dsid);
    }
}
