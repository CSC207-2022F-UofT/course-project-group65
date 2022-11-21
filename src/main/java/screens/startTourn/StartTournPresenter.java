package screens.startTourn;

import useCases.startTourn.StartTournOB;
import useCases.startTourn.StartTournOD;

/**
 * A class for the Presenter of the start tournament use case.
 */
public class StartTournPresenter implements StartTournOB {

    @Override
    public StartTournOD presentSuccess(StartTournOD outputData) {
        return outputData;
    }

    @Override
    public StartTournOD presentError(String errorMessage) {
        throw new StartTournFailed(errorMessage);
    }
}
