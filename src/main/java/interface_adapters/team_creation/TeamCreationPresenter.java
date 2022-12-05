package interface_adapters.team_creation;

import interface_adapters.view_interfaces.main_view_interfaces.TeamCreationView;
import use_cases.team_creation.teamCreationOB;
import use_cases.team_creation.teamCreationOD;


/**
 * The presenter class for teamCreation.
 */
public class TeamCreationPresenter implements teamCreationOB {

    public TeamCreationView view;

    public TeamCreationPresenter(TeamCreationView view) {
        this.view = view;
    }

    /**
     * Prepares the success view if the team was successfully created
     * @param outputData - output data from the usecase
     * @return output data
     */
    @Override
    public teamCreationOD prepareSuccessView(teamCreationOD outputData) {
        view.updateTeamMembers(outputData.getTeamToPlayers());
        view.replaceTeam(outputData.getNewTeam(), outputData.getOldTeam(), outputData.getGameToTeams());
        return outputData;
    }

    /**
     * Prepares the fail view if the team was not successfully created
     * @param error - error message that indicates why the team cannot be created
     * @return output data
     */
    @Override
    public teamCreationOD prepareFailView(String error) {
        throw new TeamCreationFailed(error);
    }
}
