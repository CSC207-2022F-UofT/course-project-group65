package interface_adapters.advance_team;
import use_cases.advance_team.AdvanceTeamOD;
import use_cases.advance_team.AdvanceTeamOB;

public class AdvanceTeamPresenter implements AdvanceTeamOB {

    /**
     * Present the output data to the user.
     *
     * @param outputData The output data to present
     */

    // The success or faliure is shown to the view, completing the process.
    public AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData){
        return outputData;
    }

    public AdvanceTeamOD presentError(String errorMessage){
        throw new AdvanceTeamFailed(errorMessage);
    }
}
