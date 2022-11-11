package screens.advanceTeam;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.advanceTeam.*;

public class AdvanceTeamController {

    AdvanceTeamIB userInput;

    public AdvanceTeamController(AdvanceTeamIB userInput) {
        this.userInput = userInput;
    }

    public AdvanceTeamOD create(int bracketID, String username, int gameID, BracketRepo bracketRepo,
                                AccountRepo accountRepo){
        AdvanceTeamID inputData = new AdvanceTeamID(bracketID, username, gameID, bracketRepo, accountRepo);

        return this.userInput.advanceWinner(inputData);
    }

}
