package screens.advanceTeam;
import useCases.advanceTeam.*;

public class AdvanceTeamPresenter implements AdvanceTeamOB {

    // The success or faliure is shown to the view, completing the process.
    public AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData){
        return outputData;
    }

    public AdvanceTeamOD presentError(String errorMessage){
        throw new AdvanceTeamFailed(errorMessage); //
    }
}
