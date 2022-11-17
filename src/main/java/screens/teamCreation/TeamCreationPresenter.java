package screens.teamCreation;

import useCases.teamCreation.teamCreationOB;
import useCases.teamCreation.teamCreationOD;

public class TeamCreationPresenter implements teamCreationOB {

    @Override
    public teamCreationOD prepareSuccessView(teamCreationOD outputData) {
        return outputData;
    }

    @Override
    public teamCreationOD prepareFailView(String error) {
        throw new TeamCreationFailed(error);
    }
}
