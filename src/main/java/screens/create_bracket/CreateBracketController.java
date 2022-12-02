package screens.create_bracket;

import database.CreateBracket.CreateBracketFileWriter;
import use_cases.create_bracket.*;
import use_cases.general_classes.InformationRecord;

public class CreateBracketController {
    /*
    A class for the Controller of the create bracket use case.
     */
    private final CreateBracketIB createBracketIB;

//    public CreateBracketController(CreateBracketIB userInput) {
//        this.createBracketIB = userInput;
//    }

    public CreateBracketController(String currentUser, InformationRecord informationRecord) {
        CreateBracketOB outputBoundary = new CreateBracketPresenter();
        CreateBracketGateway gateway = new CreateBracketFileWriter("accounts.txt", "brackets.txt");
        this.createBracketIB = new CreateBracketUC(outputBoundary, gateway, currentUser, informationRecord);
//        this.createBracketIB = createBracketIB;
    }

    public CreateBracketOD create(String bracketType, String bracketName, int numTeams, int maxTeamSize, int winCondition) {

        CreateBracketID inputData = new CreateBracketID(bracketType, bracketName, numTeams, maxTeamSize, winCondition);

        return createBracketIB.create(inputData);
    }
}
