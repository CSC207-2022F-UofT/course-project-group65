package use_cases.end_tournament;

/**
 * An interface for the input boundary of the end tournament use case.
 */
public interface EndTournIB {
    /**
     * Ends the tournament only if all the checks pass.
     */
    EndTournOD endTourn();
}
