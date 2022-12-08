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


    /**
     * Creates a new StartTournController object.
     *
     * @param username          The name of the User who attempts to start the tournament
     * @param informationRecord The class that includes both the BracketRepo and AccountRepo
     * @param bracketId         The bracket ID
     */
    public StartTournController(String username, InformationRecord informationRecord, int bracketId) {
        StartTournOB startTournOB = new StartTournPresenter();
        StartTournGateway gateway = new StartTournFilerWriter("brackets.txt");
        userInput = new StartTournUC(startTournOB, username, informationRecord, bracketId, gateway);
    }

    /**
     * The method to get potential errors that the User may have
     *
     * @return a list of strings that each indicate the type of errors that the User has.
     */
    public ArrayList<String> startTourn() {
        StartTournOD output = userInput.startTourn();
        return output.getErrors();
    }

    /**
     * The method to start the tournament.
     */
    public void start() {
        userInput.start();
    }
}
