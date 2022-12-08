package use_cases.join_tournament;

import java.io.IOException;

/**
 * The gateway interface for the JoinTournament use case.
 */
public interface JoinTournamentGateway {

    /**
     * Defines the method to save data from the join tournament use case.
     *
     * @Throws IOException if the file cannot be saved.
     */
    void save(JoinTournamentDSID input) throws IOException;
}
