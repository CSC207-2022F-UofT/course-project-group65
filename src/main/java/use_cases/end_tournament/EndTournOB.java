package use_cases.end_tournament;

/**
 * An interface for the output boundary of the end tournament use case.
 */

public interface EndTournOB {

    EndTournOD presentSuccess(EndTournOD outputData);

    EndTournOD presentError(String errorMessage);
}
