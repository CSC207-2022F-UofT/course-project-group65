package screens.teamCreation;

import useCases.teamCreation.teamCreationOB;
import useCases.teamCreation.teamCreationOD;
/**
 * The presenter class for teamCreation.
 */
public class TeamCreationPresenter implements teamCreationOB {
    /**
     * Prepares the success view if the team was successfully created
     * @param outputData - output data from the usecase
     * @return output data
     */
    @Override
    public teamCreationOD prepareSuccessView(teamCreationOD outputData) {
        return outputData;
    }
    /**
     * Prepares the fail view if the team was not successfully created
     * @param error - error message that indicates why the team cannot be created
     * @return output data
     */
    @Override
    public teamCreationOD prepareFailView(String error) {
        throw new TeamCreationFailed(error);
    }
}
