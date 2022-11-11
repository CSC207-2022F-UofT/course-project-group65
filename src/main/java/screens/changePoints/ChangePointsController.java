package screens.changePoints;
import entities.*;
import useCases.changePoints.*;

public class ChangePointsController {

    ChangePointsIB userInput; // This is actually a use case instance. But it is of type ChangePointsIB for CA purposes.

    public ChangePointsController(ChangePointsIB userInput) {
        this.userInput = userInput;
    }

    // Prepare the output data for the view
    public ChangePointsOD create(int bracketID, String username, int gameID, int newPoints, BracketRepo bracketRepo,
                                 AccountRepo accountRepo, Team team){

        // the input data is created.
        ChangePointsID inputData = new ChangePointsID(bracketID, username, gameID, newPoints, bracketRepo, accountRepo,
                team);

        // this simultaneously runs the use case (by passing in ID), and returns the output data
        // This is not using anything rn because we are using the presenter instead.
        return this.userInput.changePoints(inputData);
    }

}
