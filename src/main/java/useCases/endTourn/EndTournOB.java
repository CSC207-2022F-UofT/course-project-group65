package useCases.endTourn;

import useCases.startTourn.StartTournOD;

public interface EndTournOB {

    public EndTournOD presentSuccess(EndTournOD outputData);

    public EndTournOD presentError(String errorMessage);
}
