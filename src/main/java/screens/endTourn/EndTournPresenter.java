package screens.endTourn;

import use_cases.end_tournament.EndTournOB;
import use_cases.end_tournament.EndTournOD;

/**
 * A class for the Presenter of the end tournament use case.
 */
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
