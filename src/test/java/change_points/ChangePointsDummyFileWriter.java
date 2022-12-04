package change_points;

import entities.BracketRepo;
import use_cases.change_points.ChangePointsDSID;
import use_cases.change_points.ChangePointsGateway;

import java.io.IOException;
import java.util.HashMap;

public class ChangePointsDummyFileWriter implements ChangePointsGateway {
    final private HashMap<BracketRepo, ChangePointsDSID> bracketRepos = new HashMap<>();
    @Override
    public void save(ChangePointsDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getUpdatedBracketRepoCP());
        bracketRepos.put(dsid.getUpdatedBracketRepoCP(), dsid);
    }
}
