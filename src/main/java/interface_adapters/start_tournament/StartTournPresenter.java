package interface_adapters.start_tournament;

import use_cases.start_tournament.StartTournOB;
import use_cases.start_tournament.StartTournOD;

/**
 * A class for the Presenter of the start tournament use case.
 */
public class StartTournPresenter implements StartTournOB {

    /**
     * Prepares the success view if the tournament has been started successfully.
     *
     * @param outputData output data from use case
     * @return start tournament output data
     */
    @Override
    public StartTournOD presentSuccess(StartTournOD outputData) {
        return outputData;
    }

    /**
     * Prepares the error view if the tournament has not been started successfully.
     *
     * @param errorMessage Error message that indicates why it failed to start the tournament.
     * @return start tournament output data
     */
    @Override
    public StartTournOD presentError(String errorMessage) {
        throw new StartTournFailed(errorMessage);
    }
}
