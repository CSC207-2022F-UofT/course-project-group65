package use_cases.declare_winner;

public class DeclareWinnerID {

    private int gameID;

    public DeclareWinnerID(int gameID) {
        this.gameID = gameID;
    }

    public void setGameIDDW(int gameID) {
        this.gameID = gameID;
    }

    public int getGameIDDW() {
        return gameID;
    }

}