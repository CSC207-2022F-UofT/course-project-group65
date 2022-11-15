package useCases.endTourn;

import useCases.startTourn.StartTournOD;

public interface EndTournOB {

    public EndTournOD presentSuccess(String successMessage);

    public EndTournOD presentError(String errorMessage);
}
