package screens.endTourn;

import useCases.endTourn.EndTournOB;
import useCases.endTourn.EndTournOD;

public class EndTournPresenter implements EndTournOB {
    @Override
    public EndTournOD presentSuccess(EndTournOD outputData) {
        return outputData;
    }

    @Override
    public EndTournOD presentError(String errorMessage) {
        throw new EndTournFailed(errorMessage);
    }
}
