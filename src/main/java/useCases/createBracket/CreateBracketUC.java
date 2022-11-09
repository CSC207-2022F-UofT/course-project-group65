package useCases.createBracket;

import entities.Bracket;
import entities.BracketRepo;
import entities.User;

public class CreateBracketUC {
/*
    * This is a use case for creating a bracket.
 */
    private int bracketID = 0;
    private BracketAssembler assembler;

    public int generateBracketID() {
        this.bracketID++;
        return this.bracketID;
    }

//    USER HAS A DEPENDENCY ISSUE I BELIEVE
    public Bracket createBracket(User creator, String bracketType, String bracketName,
                           int numTeams, int maxTeamSize, int winCondition) {
        int tournamentID = generateBracketID();
        this.assembler = new BracketAssembler();
        this.assembler.assembleBracket(bracketType, creator, bracketName, tournamentID,
                numTeams, maxTeamSize, winCondition);
        return this.assembler.getBracket();
    }

    public int storeBracket(BracketRepo repo, Bracket bracket) {
//        Stores the bracket in the repo and returns the bracketID
        repo.addBracket(bracket);
        return bracket.getTournamentID();
    }

}
