package use_cases.start_tournament;

/**
 * An interface for the output boundary of the start tournament use case.
 */
public interface StartTournOB {

    StartTournOD presentSuccess(StartTournOD outputData);

    StartTournOD presentError(String errorMessage);
}
