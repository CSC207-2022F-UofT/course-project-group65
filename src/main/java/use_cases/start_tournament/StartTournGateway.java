package use_cases.start_tournament;

import java.io.IOException;

/** An interface for the gateway of the start tournament use case.
 * This interface is used for dependency inversion, allowing the use case to be independent of the database.
 */
public interface StartTournGateway {
    /** Saves the data to the database
     * @param data - the data source interface data
     */
    void save(StartTournDSID data) throws IOException;
}
