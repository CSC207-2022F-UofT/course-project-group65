package useCases.startTourn;

public interface StartTournOB {

    public StartTournOD presentSuccess(StartTournOD outputData);

    public StartTournOD presentError(String errorMessage);
}
