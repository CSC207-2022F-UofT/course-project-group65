package screens.teamCreation;

import database.TeamCreation.TeamCreationFileWriter;
import useCases.generalClasses.InformationRecord;
import useCases.teamCreation.*;


public class TeamCreationController {
    final teamCreationIB userInput;

    public TeamCreationController(InformationRecord informationRecord, int bracketID, String username) {
        teamCreationOB outputBoundary = new TeamCreationPresenter();
        teamCreationGateway gateway = new TeamCreationFileWriter("brackets.txt");
        this.userInput = new teamCreationUC(outputBoundary, gateway, username, bracketID, informationRecord);

    }

    public teamCreationOD createNewTeam(String teamName) {
        teamCreationID inputData = new teamCreationID(teamName);

        return userInput.createNewTeam(inputData);
    }
}
