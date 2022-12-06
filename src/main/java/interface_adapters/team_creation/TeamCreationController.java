package interface_adapters.team_creation;

import interface_adapters.data_interface_adapters.team_creation_data.TeamCreationFileWriter;
import use_cases.general_classes.InformationRecord;
import use_cases.team_creation.*;

/**
 * The class for the controller of teamCreation
 */

public class TeamCreationController {
    final teamCreationIB userInput;
    final TeamCreationPresenter presenter;
    private String username;
    /**
     * Creates a new TeamCreationController object
     * @param username The name of the user who created the team
     * @param informationRecord The class that stores the repositories
     * @param bracketID The current bracket id
     */
    public TeamCreationController(TeamCreationPresenter presenter, InformationRecord informationRecord, int bracketID, String username) {
        this.presenter = presenter;
        teamCreationGateway gateway = new TeamCreationFileWriter("brackets.txt");
        this.userInput = new teamCreationUC(presenter, gateway, username, bracketID, informationRecord);
        this.username = username;
    }

    public String getCurrUser(){
        return username;
    }

    /**
     * Creates the new team in the bracket
     * @param teamName The name of the team inputed by the user
     */
    public void createNewTeam(String teamName) {
        teamCreationID inputData = new teamCreationID(teamName);
        teamCreationOD outputData = userInput.createNewTeam(inputData);
        this.username = outputData.getUsername();
    }
}
