package use_cases.view_tournament;

/**
 * The output boundary for the view tournament use case.
 */
public interface ViewTournamentOB {
    /**
     * Defines the method to present a success to the user.
     *
     * @param outputData the output data.
     * @return the output data.
     */
    ViewTournamentOD prepareSuccessView(ViewTournamentOD outputData);

    /**
     * Defines the method to present a failure to the user.
     *
     * @param error the error message.
     * @return the output data.
     */
    ViewTournamentOD prepareFailView(String error);
}
