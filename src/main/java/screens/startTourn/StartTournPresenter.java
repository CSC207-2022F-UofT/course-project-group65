package screens.startTourn;

import useCases.startTourn.StartTournOB;
import useCases.startTourn.StartTournOD;

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
