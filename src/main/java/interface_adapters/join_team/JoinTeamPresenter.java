package interface_adapters.join_team;


import use_cases.join_team.JoinTeamOB;
import use_cases.join_team.JoinTeamOD;

/**
 * A class for the Presenter of the join team use case.
 */
public class JoinTeamPresenter implements JoinTeamOB {
    /**
     * Prepares the success view if the team was successfully joined
     * @param outputData - output data from the usecase
     * @return output data
     */
    @Override
    public JoinTeamOD SuccessView(JoinTeamOD outputData) {
        return outputData;
    }
    /**
     * Prepares the fail view if the team was not successfully joined
     * @param errorMessage - error message that indicates why the team cannot be joined
     * @return output data
     */
    @Override
    public JoinTeamOD FailView(String errorMessage) {
        throw new JoinTeamFailed(errorMessage);
    }
}
