package assign_observer;

import entities.BracketRepo;
import use_cases.assign_observer.AssignObserverDSID;
import use_cases.assign_observer.AssignObserverGateway;

import java.io.IOException;
import java.util.HashMap;
/** This class represents the assignObserver file writer for the test use*/
public class AssignObserverDummyFileWriter implements AssignObserverGateway {
    /** The informationRecord variable used to record all the information */
    final private HashMap<BracketRepo, AssignObserverDSID> bracketRepos = new HashMap<>();
    /**
     * Write the given data to the file.
     *
     * @param dsid The data to write
     * @throws IOException If an error occurs while writing to the file
     */
    @Override
    public void save(AssignObserverDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getBracketRepo());
        bracketRepos.put(dsid.getBracketRepo(), dsid);
    }
}
