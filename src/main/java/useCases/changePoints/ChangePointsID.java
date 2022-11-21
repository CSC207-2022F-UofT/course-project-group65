package useCases.changePoints;
import entities.*;

public class ChangePointsID {

    private int gameID, newPoints;
    private String teamName;

    public ChangePointsID(int gameID, int newPoints, String teamName) {
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
