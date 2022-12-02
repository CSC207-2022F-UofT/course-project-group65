package screens.start_tournament;


import database.StartTourn.StartTournFilerWriter;
import use_cases.general_classes.InformationRecord;
import use_cases.start_tournament.*;

/**
 * A class for the Controller of the start tournament use case.
 */
public class StartTournController {
    final StartTournIB userInput;

    public StartTournController(String username, InformationRecord informationRecord, int bracketId) {
        StartTournOB startTournOB = new StartTournPresenter();
        StartTournGateway gateway = new StartTournFilerWriter("brackets.txt");
        userInput = new StartTournUC(startTournOB, username, informationRecord, bracketId, gateway);
    }

    public StartTournOD startTourn() {
        return userInput.startTourn();
    }
    public void start(){
        userInput.start();
    }
}
