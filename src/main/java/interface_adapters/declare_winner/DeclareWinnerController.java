package interface_adapters.declare_winner;

import interface_adapters.data_interface_adapters.declare_winner_data.DeclareWinnerFileWriter;
import interface_adapters.view_interfaces.main_view_interfaces.DeclareWinnerView;
import use_cases.declare_winner.*;
import use_cases.general_classes.InformationRecord;

/**
 * This class is the controller for the declare winner use case.
 * It is responsible for the interaction between the user and the declare winner use case.
 * It is also responsible for the interaction between the declare winner use case and the data interface adapters.
 */
public class DeclareWinnerController {

    DeclareWinnerIB userInput;
    /**
     * This constructor creates a new DeclareWinnerController object.
     * @param informationRecord The information record that the declare winner use case will use.
     * @param bracketID The ID of the bracket that the game in question is in.
     * @param username The username of the user that is declaring the winner.
     */
    public DeclareWinnerController(DeclareWinnerPresenter presenter, InformationRecord informationRecord, int bracketID, String username) {
        DeclareWinnerGateway gateway = new DeclareWinnerFileWriter("brackets.txt");
        this.userInput = new DeclareWinnerUC(presenter, gateway, informationRecord, bracketID, username);
    }

    /**
     * This method is called when the user wants to declare a winner.
     * @param gameID The ID of the game that the user wants to declare a winner for.
     * @return The output of the declare winner use case.
     */
    public DeclareWinnerOD create(int gameID){
        DeclareWinnerID inputData = new DeclareWinnerID(gameID);
        return this.userInput.setWinner(inputData);
    }

}
