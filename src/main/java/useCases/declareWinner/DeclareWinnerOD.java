package useCases.declareWinner;
import entities.*;

public class DeclareWinnerOD {
    private int finishedGameID;
    private String winningTeamName;

    public DeclareWinnerOD(int finishedGameID, String winningTeamName){
        this.finishedGameID = finishedGameID;
        this.winningTeamName = winningTeamName;
    }

    public int getFinishedGameID() {
        return finishedGameID;
    }

    public String getWinningTeamName() {
        return winningTeamName;
    }

    public void setFinishedGameID(int finishedGameID) {
        this.finishedGameID = finishedGameID;
    }

    public void setWinningTeamName(String winningTeamName) {
        this.winningTeamName = winningTeamName;
    }
}
