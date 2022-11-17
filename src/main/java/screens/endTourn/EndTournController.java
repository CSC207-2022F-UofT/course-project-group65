package screens.endTourn;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;
import useCases.endTourn.EndTournIB;
import useCases.endTourn.EndTournID;
import useCases.endTourn.EndTournOD;

/**
 * A class for the Controller of the end tournament use case.
 */
public class EndTournController {
    final EndTournIB userInput;

    public EndTournController(EndTournIB userInput) {
        this.userInput = userInput;
    }

    public EndTournOD endTourn(BracketRepo brackets, String currentUser, AccountRepo accountRepo, int bracketId) {
        EndTournID inputData = new EndTournID(brackets, currentUser, accountRepo, bracketId);
        return userInput.endTourn(inputData);
    }

}
