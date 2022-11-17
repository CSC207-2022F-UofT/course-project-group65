package useCases.createBracket;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;

public class CreateBracketID {
    /*
    The data class for the create bracket input boundary.
     */

    private BracketRepo brackets;
    private String bracketType;
    private String bracketName;
    private int numTeams;
    private int maxTeamSize;
    private int winCondition;

    public CreateBracketID(String bracketType, String bracketName, int numTeams, int maxTeamSize, int winCondition) {
        this.bracketType = bracketType;
        this.bracketName = bracketName;
        this.numTeams = numTeams;
        this.maxTeamSize = maxTeamSize;
        this.winCondition = winCondition;
    }

    public String getBracketType() {
        return bracketType;
    }

    public String getBracketName() {
        return bracketName;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    public int getWinCondition() {
        return winCondition;
    }
}
