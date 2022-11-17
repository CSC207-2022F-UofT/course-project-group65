package useCases.endTourn;

/**
 *  An interface for the output boundary of the end tournament use case.
 */

public interface EndTournOB {

    public EndTournOD presentSuccess(EndTournOD outputData);

    public EndTournOD presentError(String errorMessage);
}
