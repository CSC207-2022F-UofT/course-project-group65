package screens.endTourn;

import useCases.endTourn.EndTournIB;
import useCases.endTourn.EndTournOD;

/**
 * A class for the Controller of the end tournament use case.
 */
public class EndTournController {
    final EndTournIB userInput;

    public EndTournController(EndTournIB userInput) {
        this.userInput = userInput;
    }

    //
    public EndTournOD endTourn() {
        return userInput.endTourn();
    }

}
