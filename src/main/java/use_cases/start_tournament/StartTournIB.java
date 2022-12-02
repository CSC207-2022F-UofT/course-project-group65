package use_cases.start_tournament;

/**
 * An interface for the input boundary of the start tournament use case.
 */
public interface StartTournIB {
    /**
     * Starts the tournament if:
     * 1. all the checks pass, or
     * 2. the User is Overseer and wants to start anyway.
     */
    StartTournOD startTourn();

    /**
     * Depending on the checks and above-mentioned conditions, it sets the tournament condition.
     */
    void start();
}
