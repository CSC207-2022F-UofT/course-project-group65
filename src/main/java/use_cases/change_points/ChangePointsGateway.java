package use_cases.change_points;

import java.io.IOException;

/** The gateway interface for the ChangePoints use case. */
public interface ChangePointsGateway {

    /** Defines the method to save data from the change points use case.
     * @Throws IOException if the file cannot be saved. */
    void save(ChangePointsDSID data) throws IOException;

}
