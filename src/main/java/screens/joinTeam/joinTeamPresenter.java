package screens.joinTeam;


import useCases.joinTeam.joinTeamOB;
import useCases.joinTeam.joinTeamOD;

public class joinTeamPresenter implements joinTeamOB {
    @Override
    public joinTeamOD SuccessView(joinTeamOD outputData) {
        return outputData;
    }

    @Override
    public joinTeamOD FailView(String errorMessage) {
        throw new joinTeamFailed(errorMessage);
    }
}
