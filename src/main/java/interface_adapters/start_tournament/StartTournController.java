package interface_adapters.start_tournament;


import interface_adapters.data_interface_adapters.start_tournament_data.StartTournFilerWriter;
import use_cases.general_classes.InformationRecord;
import use_cases.start_tournament.*;

import java.util.ArrayList;

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

    public ArrayList<String> startTourn() {
        StartTournOD output = userInput.startTourn();
        return output.getErrors();
    }

    public void start() {
        userInput.start();
    }
}
