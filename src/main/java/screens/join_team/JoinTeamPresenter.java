package screens.join_team;


import use_cases.join_team.JoinTeamOB;
import use_cases.join_team.JoinTeamOD;

/**
 * A class for the Presenter of the join team use case.
 */
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
