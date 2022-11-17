package screens.startTourn;

import entities.Bracket;
import entities.User;
import useCases.startTourn.StartTournIB;
import useCases.startTourn.StartTournID;
import useCases.startTourn.StartTournOD;

public class StartTournController {
    final StartTournIB userInput;

    public StartTournController(StartTournIB userInput) {
        this.userInput = userInput;
    }

    public StartTournOD startTourn(User user, Bracket bracket) {
        StartTournID inputData = new StartTournID(user, bracket);

        return userInput.startTourn(inputData);
    }
}
