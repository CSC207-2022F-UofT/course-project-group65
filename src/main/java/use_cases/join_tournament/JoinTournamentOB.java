package use_cases.join_tournament;

/**
 * The output boundary for the join tournament use case.
 */
public interface JoinTournamentOB {
    /**
     * Defines the method to present a success to the user.
     *
     * @param outputData the output data.
     * @return the output data.
     */
    JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData);

    /**
     * Defines the method to present a failure to the user.
     *
     * @param error the error message.
     * @return the output data.
     */
    JoinTournamentOD prepareFailView(String error);
}
