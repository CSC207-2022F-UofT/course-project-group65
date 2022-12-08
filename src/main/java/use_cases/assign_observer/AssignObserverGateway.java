package use_cases.assign_observer;

import java.io.IOException;

/**
 * The gateway interface for the AssignObserver use case.
 */
public interface AssignObserverGateway {
    /**
     * Defines the method to save data from the assign observer use case.
     *
     * @Throws IOException if the file cannot be saved.
     */
    void save(AssignObserverDSID input) throws IOException;
}
