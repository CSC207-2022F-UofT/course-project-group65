package useCases.startTourn;

/**
 * An interface for the input boundary of the start tournament use case.
 */
public interface StartTournIB {
    StartTournOD startTourn(StartTournID startTournID);
}
