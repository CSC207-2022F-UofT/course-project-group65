package use_cases.assign_observer;

import java.util.LinkedHashMap;

public class AssignObserverOD {
    private final String assignee;
    private final int gameID;
    private final LinkedHashMap<Integer, String> gameToReferee;

    public AssignObserverOD(String assignee, int gameID, LinkedHashMap<Integer, String> gameToReferee) {
        this.assignee = assignee;
        this.gameID = gameID;
        this.gameToReferee = gameToReferee;
    }

    public LinkedHashMap<Integer, String> getGameToReferee() {
        return gameToReferee;
    }

    public int getGameID() {
        return gameID;
    }

    public String getAssignee() {
        return assignee;
    }
}
