package interface_adapters.create_bracket;

import interface_adapters.NextScreenData;
import interface_adapters.data_interface_adapters.create_bracket_data.CreateBracketFileWriter;
import use_cases.create_bracket.*;
import use_cases.general_classes.InformationRecord;

public class CreateBracketController {
    /*
    A class for the Controller of the create bracket use case.
     */
    private final CreateBracketIB createBracketIB;
    private final CreateBracketPresenter createBracketPresenter;

    public CreateBracketController(String currentUser, InformationRecord informationRecord) {
        this.createBracketPresenter = new CreateBracketPresenter();
        CreateBracketGateway gateway = new CreateBracketFileWriter("accounts.txt", "brackets.txt");
        this.createBracketIB = new CreateBracketUC(this.createBracketPresenter, gateway, currentUser, informationRecord);
    }

    public void setPresenterData(NextScreenData nextScreenData) {
        this.createBracketPresenter.setNextScreenData(nextScreenData);
    }

    public CreateBracketOD create(String bracketType, String bracketName, int numTeams, int maxTeamSize, int winCondition) {

        CreateBracketID inputData = new CreateBracketID(bracketType, bracketName, numTeams, maxTeamSize, winCondition);

        return createBracketIB.create(inputData);
    }
}
