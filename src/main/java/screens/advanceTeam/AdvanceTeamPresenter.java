package screens.advanceTeam;
import useCases.advanceTeam.*;

public class AdvanceTeamPresenter {
    public AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData){
        return outputData;
    }

    public AdvanceTeamOD presentError(String errorMessage){
        throw new AdvanceTeamFailed(errorMessage); //
    }
}
