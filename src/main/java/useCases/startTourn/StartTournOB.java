package useCases.startTourn;

/**
 *  An interface for the output boundary of the start tournament use case.
 */
public interface StartTournOB {

    public StartTournOD presentSuccess(StartTournOD outputData);

    public StartTournOD presentError(String errorMessage);
}
