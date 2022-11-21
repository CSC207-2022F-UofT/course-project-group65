package useCases.assignObserver;

public class AssignObserverOD {
    String assignee;
    int gameID, roundNum;

    public AssignObserverOD(String assignee, int gameID, int roundNum){
        this.assignee = assignee;
        this.gameID = gameID;
        this.roundNum = roundNum;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }
}
