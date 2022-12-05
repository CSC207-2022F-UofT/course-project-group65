package interface_adapters.advance_team;
import interface_adapters.view_interfaces.main_view_interfaces.AdvanceTeamView;
import use_cases.advance_team.AdvanceTeamOD;
import use_cases.advance_team.AdvanceTeamOB;

/** The presenter class of teh advance team use case. */
public class AdvanceTeamPresenter implements AdvanceTeamOB {

    private final AdvanceTeamView view;

    public AdvanceTeamPresenter(AdvanceTeamView view) {
        this.view = view;
    }

    /**
     * Present the output data to the user.
     *
     * @param outputData The output data to present
     * @return The output data.
     */

    public AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData){
        int advGameID = outputData.getAdvancedGame();
        view.updateGameScore(advGameID, outputData.getGameToTeams().get(advGameID), outputData.getGameToScores().get(advGameID));
        return outputData;
    }

    /** Presents a faliure exception to the user. */
    public AdvanceTeamOD presentError(String errorMessage){
        throw new AdvanceTeamFailed(errorMessage);
    }
}
