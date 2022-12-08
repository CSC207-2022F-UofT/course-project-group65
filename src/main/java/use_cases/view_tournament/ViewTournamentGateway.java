package use_cases.view_tournament;

import java.io.IOException;

/**
 * The gateway interface for the ViewTournament use case.
 */
public interface ViewTournamentGateway {

    /**
     * Defines the method to save data from the view tournament use case.
     *
     * @Throws IOException if the file cannot be saved.
     */
    void save(ViewTournamentDSID dsid) throws IOException;
}
