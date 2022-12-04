package use_cases.end_tournament;


import java.io.IOException;

/**
 * An interface for the gateway of the end tournament use case.
 * This interface is used for dependency inversion, allowing the use case to be independent of the database.
 */
public interface EndTournGateway {
    /**
     * Saves the data to the database
     *
     * @param data - the data source interface data
     */
    void save(EndTournDSID data) throws IOException;
}
