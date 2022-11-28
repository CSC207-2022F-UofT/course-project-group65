package screens.teamCreation;

import database.TeamCreation.TeamCreationFileWriter;
import useCases.teamCreation.*;


public class TeamCreationController {
    final teamCreationIB userInput;

    public TeamCreationController(Object bracketRepo, Object accountRepo, int bracketID, String username) {
        teamCreationOB outputBoundary = new TeamCreationPresenter();
        teamCreationGateway gateway = new TeamCreationFileWriter("brackets.txt");
        this.userInput = new teamCreationUC(outputBoundary, username, bracketID, accountRepo,
                bracketRepo);

    }

    public teamCreationOD createNewTeam(String teamName) {
        teamCreationID inputData = new teamCreationID(teamName);

        return userInput.createNewTeam(inputData);
    }
}
