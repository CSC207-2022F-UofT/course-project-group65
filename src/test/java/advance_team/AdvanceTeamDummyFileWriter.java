package advance_team;

import entities.BracketRepo;
import use_cases.advance_team.AdvanceTeamDSID;
import use_cases.advance_team.AdvanceTeamGateway;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents the advanceTeam file writer for the test use
 */
public class AdvanceTeamDummyFileWriter implements AdvanceTeamGateway {
    /**
     * This hashmap variable is for storing bracketRepos
     */
    final private HashMap<BracketRepo, AdvanceTeamDSID> bracketRepos = new HashMap<>();

    /**
     * Write the given data to the file.
     *
     * @param dsid The data to write
     * @throws IOException If an error occurs while writing to the file
     */
    @Override
    public void save(AdvanceTeamDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getUpdatedBracketRepo());
        bracketRepos.put(dsid.getUpdatedBracketRepo(), dsid);
    }
}
