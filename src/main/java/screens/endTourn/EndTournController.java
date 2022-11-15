package screens.endTourn;

import entities.Bracket;
import entities.User;
import useCases.endTourn.EndTournIB;
import useCases.endTourn.EndTournID;
import useCases.endTourn.EndTournOD;

public class EndTournController {
    final EndTournIB userInput;

    public EndTournController(EndTournIB userInput) {
        this.userInput = userInput;
    }

    public EndTournOD endTourn(User user, Bracket bracket) {
        EndTournID inputData = new EndTournID(user, bracket);

        return userInput.endTourn(inputData);
    }

}
