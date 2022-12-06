package assign_observer;

import entities.BracketRepo;
import use_cases.assign_observer.AssignObserverDSID;
import use_cases.assign_observer.AssignObserverGateway;

import java.io.IOException;
import java.util.HashMap;

public class AssignObserverDummyFileWriter implements AssignObserverGateway {
    final private HashMap<BracketRepo, AssignObserverDSID> bracketRepos = new HashMap<>();

    @Override
    public void save(AssignObserverDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}
