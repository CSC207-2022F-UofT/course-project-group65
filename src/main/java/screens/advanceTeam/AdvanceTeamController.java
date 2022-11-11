package screens.advanceTeam;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.advanceTeam.*;

public class AdvanceTeamController {

    AdvanceTeamIB userInput; // This is actually a use case instance. But it is of type AdvanceTeamIB for CA purposes.

    public AdvanceTeamController(AdvanceTeamIB userInput) {
        this.userInput = userInput;
    }

    // Prepare the output data for the view
    public AdvanceTeamOD create(int bracketID, String username, int gameID, BracketRepo bracketRepo,
                                AccountRepo accountRepo){

        // the input data is created.
        AdvanceTeamID inputData = new AdvanceTeamID(bracketID, username, gameID, bracketRepo, accountRepo);

        // this simultaneously runs the use case (by passing in ID), and returns the output data
        // This is not using anything rn because we are using the presenter instead.
        return this.userInput.advanceWinner(inputData);
    }

}
