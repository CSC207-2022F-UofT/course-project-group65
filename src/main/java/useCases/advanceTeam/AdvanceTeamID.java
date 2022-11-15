package useCases.advanceTeam;

public class AdvanceTeamID {
    private int bracketID;
    private String username;
    private int gameID;


    public AdvanceTeamID(int bracketID, String username, int gameID) {
        this.bracketID = bracketID;
        this.username = username;
        this.gameID = gameID;
    }


    public void setBracketID(int bracketID) {
        this.bracketID = bracketID;
    }

    public void setUsernameAT(String username) {
        this.username = username;
    }

    public String getUsernameAT() {
        return this.username;
    }

    public int getBracketIDAT() {
        return this.bracketID;
    }

    public void setGameIDAT(int gameID) {
        this.gameID = gameID;
    }

    public int getGameIDAT() {
        return gameID;
    }
}
