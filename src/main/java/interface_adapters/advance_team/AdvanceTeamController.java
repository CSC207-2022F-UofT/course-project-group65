package interface_adapters.advance_team;
import interface_adapters.data_interface_adapters.advance_team_data.AdvanceTeamFileWriter;
import use_cases.advance_team.*;
import use_cases.general_classes.InformationRecord;

public class AdvanceTeamController {

    AdvanceTeamIB userInput; // This is actually a use case instance. But it is of type AdvanceTeamIB for CA purposes.

//    /**
//     * Construct a controller instance for this use case with the given input boundary.
//     *
//     * @param userInput The AdvanceTeamIB to use
//     */

//    public AdvanceTeamController(AdvanceTeamIB userInput) {
//        this.userInput = userInput;
//    }

    public AdvanceTeamController(InformationRecord informationRecord, int bracketID, String username) {
        //this.userInput = userInput;
        AdvanceTeamOB outputBoundary = new AdvanceTeamPresenter();
        AdvanceTeamGateway gateway = new AdvanceTeamFileWriter("brackets.txt");
        this.userInput = new AdvanceTeamUC(outputBoundary, gateway, informationRecord, bracketID, username);
    }
    // Prepare the output data for the view
    public AdvanceTeamOD create(int gameID){

        // the input data is created.
        AdvanceTeamID inputData = new AdvanceTeamID(gameID);

        // this simultaneously runs the use case (by passing in ID), and returns the output data
        // This is not using anything rn because we are using the presenter instead.
        return this.userInput.advanceWinner(inputData);
    }

}