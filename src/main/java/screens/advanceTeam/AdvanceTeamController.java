package screens.advanceTeam;
import useCases.advanceTeam.AdvanceTeamIB;
import useCases.advanceTeam.AdvanceTeamID;
import useCases.advanceTeam.AdvanceTeamOD;

public class AdvanceTeamController {

    AdvanceTeamIB userInput; // This is actually a use case instance. But it is of type AdvanceTeamIB for CA purposes.

    /**
     * Construct a controller instance for this use case with the given input boundary.
     *
     * @param userInput The AdvanceTeamIB to use
     */

    public AdvanceTeamController(AdvanceTeamIB userInput) {
        this.userInput = userInput;
    }

    // Prepare the output data for the view
    public AdvanceTeamOD create(int bracketID, String username, int gameID){

        // the input data is created.
        AdvanceTeamID inputData = new AdvanceTeamID(bracketID, username, gameID);

        // this simultaneously runs the use case (by passing in ID), and returns the output data
        // This is not using anything rn because we are using the presenter instead.
        return this.userInput.advanceWinner(inputData);
    }

}
