package useCases.changePoints;
import entities.*;

public class ChangePointsID {

    private int bracketID;
    private String username;
    private int gameID;
    private int newPoints;
    // private Team team;
    private String teamName;

    public ChangePointsID(int bracketID, String username, int gameID, int newPoints, String teamName) {
        this.bracketID = bracketID;
        this.username = username;
        this.gameID = gameID;
        this.newPoints = newPoints;
        this.teamName = teamName;
    }

    public void setTeamNameCPID(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNameCPID() {
        return this.teamName;
    }

    public void setBracketIDCP(int bracketID) {
        this.bracketID = bracketID;
    }

    public void setUsernameCP(String username) {
        this.username = username;
    }

    public String getUsernameCP() {
        return this.username;
    }

    public int getBracketIDCP() {
        return this.bracketID;
    }

    public void setGameIDCP(int gameID) {
        this.gameID = gameID;
    }

    public void setNewPoints(int newPoints) {
        this.newPoints = newPoints;
    }

    public int getGameIDCP() {
        return gameID;
    }

    public int getNewPointsCP() {
        return newPoints;
    }


}
