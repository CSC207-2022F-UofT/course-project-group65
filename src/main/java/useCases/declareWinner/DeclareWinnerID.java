package useCases.declareWinner;

public class DeclareWinnerID {

    private int bracketID;
    private String username;
    private int gameID;

    public DeclareWinnerID(int bracketID, String username, int gameID) {
        this.bracketID = bracketID;
        this.username = username;
        this.gameID = gameID;
    }

    public void setBracketID(int bracketID) {
        this.bracketID = bracketID;
    }

    public void setUsernameDW(String username) {
        this.username = username;
    }

    public void setGameIDDW(int gameID) {
        this.gameID = gameID;
    }

    public int getGameIDDW() {
        return gameID;
    }

    public int getBracketIDDW() {
        return bracketID;
    }

    public String getUsernameDW() {
        return username;
    }

}
