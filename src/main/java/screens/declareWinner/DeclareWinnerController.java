package screens.declareWinner;
import entities.*;
import useCases.declareWinner.*;

public class DeclareWinnerController {

        DeclareWinnerIB userInput; // This is actually a use case instance. But it is of type DeclareWinnerIB for CA purposes.

        public DeclareWinnerController(DeclareWinnerIB userInput) {
            this.userInput = userInput;
        }

        // Prepare the output data for the view
        public DeclareWinnerOD create(int bracketID, String username, int gameID, BracketRepo bracketRepo,
                                    AccountRepo accountRepo){

            // the input data is created.
            DeclareWinnerID inputData = new DeclareWinnerID(bracketID, username, gameID, bracketRepo, accountRepo);

            // this simultaneously runs the use case (by passing in ID), and returns the output data
            // This is not using anything rn because we are using the presenter instead.
            return this.userInput.setWinner(inputData);
        }

}
