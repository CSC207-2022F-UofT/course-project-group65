package useCases.startTourn;

public interface StartTournOB {

    public StartTournOD presentSuccess(String successMessage);

    public StartTournOD presentError(String errorMessage);
}
