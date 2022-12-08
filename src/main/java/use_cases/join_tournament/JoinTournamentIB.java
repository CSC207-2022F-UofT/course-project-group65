package use_cases.join_tournament;

/**
 * The input boundary for join tournament.
 */
public interface JoinTournamentIB {
    /**
     * Defines the method to be called to activate use case.
     *
     * @param input The input data for the use case.
     * @return The output data for the use case.
     */
    JoinTournamentOD joinBracket(JoinTournamentID input);
}
