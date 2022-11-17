package screens.createBracket;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.createBracket.CreateBracketIB;
import useCases.createBracket.CreateBracketID;
import useCases.createBracket.CreateBracketOD;

public class CreateBracketController {
    /*
    A class for the Controller of the create bracket use case.
     */
    private CreateBracketIB createBracketIB;

    public CreateBracketController(CreateBracketIB userInput) {
        this.createBracketIB = userInput;
    }

    public CreateBracketOD create(String bracketType, String bracketName, int numTeams, int maxTeamSize, int winCondition) {

        CreateBracketID inputData = new CreateBracketID(bracketType, bracketName, numTeams, maxTeamSize, winCondition);

        return createBracketIB.create(inputData);
    }
}
