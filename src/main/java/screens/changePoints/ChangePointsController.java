package screens.changePoints;

import database.ChangePoints.ChangePointsFileWriter;
import useCases.changePoints.*;

public class ChangePointsController {

    ChangePointsIB userInput; // This is actually a use case instance. But it is of type ChangePointsIB for CA purposes.

//    /**
//     * Construct a controller for the change points use case with the given input.
//     *
//     * @param userInput The ChangePointsIB to use
//     */

//    public ChangePointsController(ChangePointsIB userInput) {
//        this.userInput = userInput;
//    }

    public ChangePointsController(Object accountRepo, Object bracketRepo, int bracketID, String username) {
        ChangePointsOB outputBoundary = new ChangePointsPresenter();
        ChangePointsGateway gateway = new ChangePointsFileWriter("brackets.txt");
        this.userInput = new ChangePointsUC(outputBoundary, gateway, accountRepo, bracketRepo, bracketID, username);
    }

    // Prepare the output data for the view
    public ChangePointsOD create(int gameID, int newPoints, String teamName) {

        // the input data is created.
        // Note: I cannot directly pass in a team object. So I have to pass in the team name as the identifier.
        // Ensure team names are unique.
        ChangePointsID inputData = new ChangePointsID(gameID, newPoints, teamName);

        // this simultaneously runs the use case (by passing in ID), and returns the output data
        // This is not using anything rn because we are using the presenter instead.
        return this.userInput.changePoints(inputData);
    }

}
