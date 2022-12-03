package use_cases.assign_observer;

import java.util.LinkedHashMap;

public class AssignObserverOD {
    String assignee;
    int gameID, roundNum;
    LinkedHashMap<Integer, String> gameToReferee;

    public AssignObserverOD(String assignee, int gameID, int roundNum, LinkedHashMap<Integer, String> gameToReferee) {
        this.assignee = assignee;
        this.gameID = gameID;
        this.roundNum = roundNum;
        this.gameToReferee = gameToReferee;
    }

    public LinkedHashMap<Integer, String> getGameToReferee() {
        return gameToReferee;
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
