package interface_adapters.end_tournament;

import use_cases.end_tournament.EndTournOB;
import use_cases.end_tournament.EndTournOD;

/**
 * A class for the Presenter of the end tournament use case.
 */
public class EndTournPresenter implements EndTournOB {
    /**
     * Prepares the success view if the tournament has been ended successfully.
     *
     * @param outputData output data from use case
     * @return end tournament output data
     */
    @Override
    public EndTournOD presentSuccess(EndTournOD outputData) {
        return outputData;
    }

    /**
     * Prepares the error view if the tournament has not been ended successfully.
     *
     * @param errorMessage Error message that indicates why it failed to end the tournament.
     * @return end tournament output data
     */
    @Override
    public EndTournOD presentError(String errorMessage) {
        throw new EndTournFailed(errorMessage);
    }
}
