package change_points;

import entities.BracketRepo;
import use_cases.change_points.ChangePointsDSID;
import use_cases.change_points.ChangePointsGateway;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents the changePoints file writer for the test use
 */
public class ChangePointsDummyFileWriter implements ChangePointsGateway {
    /**
     * This hashmap variable is for storing bracketRepos
     */
    final private HashMap<BracketRepo, ChangePointsDSID> bracketRepos = new HashMap<>();

    /**
     * Write the given data to the file.
     *
     * @param dsid The data to write
     * @throws IOException If an error occurs while writing to the file
     */
    @Override
    public void save(ChangePointsDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getUpdatedBracketRepoCP());
        bracketRepos.put(dsid.getUpdatedBracketRepoCP(), dsid);
    }
}
