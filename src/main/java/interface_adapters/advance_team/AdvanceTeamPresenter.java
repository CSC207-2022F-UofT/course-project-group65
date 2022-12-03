package interface_adapters.advance_team;
import use_cases.advance_team.AdvanceTeamOD;
import use_cases.advance_team.AdvanceTeamOB;

/** The presenter class of teh advance team use case. */
public class AdvanceTeamPresenter implements AdvanceTeamOB {

    /**
     * Present the output data to the user.
     *
     * @param outputData The output data to present
     * @return The output data.
     */

    public AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData){
        return outputData;
    }

    /** Presents a faliure exception to the user. */
    public AdvanceTeamOD presentError(String errorMessage){
        throw new AdvanceTeamFailed(errorMessage);
    }
}
