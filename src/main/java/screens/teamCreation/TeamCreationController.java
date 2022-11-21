package screens.teamCreation;

import useCases.teamCreation.teamCreationIB;
import useCases.teamCreation.teamCreationID;
import useCases.teamCreation.teamCreationOD;


public class TeamCreationController {
    final teamCreationIB userInput;

    public TeamCreationController(teamCreationIB userInput) {
        this.userInput = userInput;
    }

    public teamCreationOD createNewTeam(String teamName) {
        teamCreationID inputData = new teamCreationID(teamName);

        return userInput.createNewTeam(inputData);
    }
}
