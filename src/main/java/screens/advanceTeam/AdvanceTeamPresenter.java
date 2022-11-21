package screens.advanceTeam;
import useCases.advanceTeam.AdvanceTeamOD;
import useCases.advanceTeam.AdvanceTeamOB;

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
