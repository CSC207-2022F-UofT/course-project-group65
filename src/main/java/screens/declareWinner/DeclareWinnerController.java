package screens.declareWinner;

import useCases.declareWinner.DeclareWinnerIB;
import useCases.declareWinner.DeclareWinnerID;
import useCases.declareWinner.DeclareWinnerOD;

public class DeclareWinnerController {

    DeclareWinnerIB userInput; // This is actually a use case instance. But it is of type DeclareWinnerIB for CA purposes.

    /**
     * Construct a DeclareWinnerController instance with the given DeclareWinnerIB.
     *
     * @param userInput The DeclareWinnerIB to use
     */

    public DeclareWinnerController(DeclareWinnerIB userInput) {
        this.userInput = userInput;
    }

    // Prepare the output data for the view
    public DeclareWinnerOD create(int bracketID, String username, int gameID){

        // the input data is created.
        DeclareWinnerID inputData = new DeclareWinnerID(bracketID, username, gameID);

        // this simultaneously runs the use case (by passing in ID), and returns the output data
        // This is not using anything rn because we are using the presenter instead.
        return this.userInput.setWinner(inputData);
    }

}
