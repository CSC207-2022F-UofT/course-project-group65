package screens.startTourn;

import useCases.startTourn.StartTournIB;
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
