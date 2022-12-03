package AssignObserver;

import entities.BracketRepo;
import use_cases.assign_observer.AssignObserverDSID;
import use_cases.assign_observer.AssignObserverGateway;

import java.util.HashMap;

public class AssignObserverInMemoryBracket implements AssignObserverGateway {
    final private HashMap<BracketRepo, AssignObserverDSID> bracketRepos = new HashMap<>();

    @Override
    public void save(AssignObserverDSID dsid) throws Exception {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}
