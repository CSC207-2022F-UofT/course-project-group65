package screens.joinTeam;


import useCases.joinTeam.JoinTeamOB;
import useCases.joinTeam.JoinTeamOD;

public class JoinTeamPresenter implements JoinTeamOB {
    @Override
    public JoinTeamOD SuccessView(JoinTeamOD outputData) {
        return outputData;
    }

    @Override
    public JoinTeamOD FailView(String errorMessage) {
        throw new JoinTeamFailed(errorMessage);
    }
}
