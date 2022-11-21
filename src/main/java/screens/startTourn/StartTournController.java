package screens.startTourn;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;
import useCases.startTourn.StartTournIB;
import useCases.startTourn.StartTournID;
import useCases.startTourn.StartTournOD;

/**
 * A class for the Controller of the start tournament use case.
 */
public class StartTournController {
    final StartTournIB userInput;

    public StartTournController(StartTournIB userInput) {
        this.userInput = userInput;
    }

    public StartTournOD startTourn() {
        return userInput.startTourn();
    }
    public void start(){
        userInput.start();
    }
}
