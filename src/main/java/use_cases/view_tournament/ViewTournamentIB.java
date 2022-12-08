package use_cases.view_tournament;

/**
 * The input boundary for view tournament.
 */
public interface ViewTournamentIB {
    /**
     * Defines the method to be called to activate use case.
     *
     * @param input The input data for the use case.
     * @return The output data for the use case.
     */
    ViewTournamentOD viewBracket(ViewTournamentID input);
}
