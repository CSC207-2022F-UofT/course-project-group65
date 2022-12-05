package interface_adapters.end_tournament;

import interface_adapters.data_interface_adapters.end_tournament_data.EndTournFileWriter;
import use_cases.end_tournament.*;
import use_cases.general_classes.InformationRecord;

/**
 * A class for the Controller of the end tournament use case.
 */
public class EndTournController {
    final EndTournIB userInput;

    /**
     * @param username          The name of the User who attempts to end the tournament.
     * @param informationRecord The class that includes both the BracketRepo and AccountRepo
     * @param bracketId         The bracket ID
     */
    public EndTournController(String username, InformationRecord informationRecord, int bracketId) {
        EndTournOB endTournOB = new EndTournPresenter();
        EndTournGateway gateway = new EndTournFileWriter("brackets.txt");
        userInput = new EndTournUC(endTournOB, username, informationRecord, bracketId, gateway);
    }

    /**
     * The method to end the tournament.
     */
    public void endTourn() {
        userInput.endTourn();
    }

}
