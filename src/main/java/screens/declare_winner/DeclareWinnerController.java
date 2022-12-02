package screens.declare_winner;

import database.DeclareWinner.DeclareWinnerFileWriter;
import use_cases.declare_winner.*;
import use_cases.general_classes.InformationRecord;

public class DeclareWinnerController {

    DeclareWinnerIB userInput; // This is actually a use case instance. But it is of type DeclareWinnerIB for CA purposes.

//    /**
//     * Construct a DeclareWinnerController instance with the given DeclareWinnerIB.
//     *
//     * @param userInput The DeclareWinnerIB to use
//     */

//    public DeclareWinnerController(DeclareWinnerIB userInput) {
//        this.userInput = userInput;
//    }

    public DeclareWinnerController(InformationRecord informationRecord, int bracketID, String username) {
        // this.userInput = userInput;
        DeclareWinnerOB outputBoundary = new DeclareWinnerPresenter();
        DeclareWinnerGateway gateway = new DeclareWinnerFileWriter("brackets.txt");
        this.userInput = new DeclareWinnerUC(outputBoundary, gateway, informationRecord, bracketID, username);
    }

    // Prepare the output data for the view
    public DeclareWinnerOD create(int gameID){

        // the input data is created.
        DeclareWinnerID inputData = new DeclareWinnerID(gameID);

        // this simultaneously runs the use case (by passing in ID), and returns the output data
        // This is not using anything rn because we are using the presenter instead.
        return this.userInput.setWinner(inputData);
    }

}
