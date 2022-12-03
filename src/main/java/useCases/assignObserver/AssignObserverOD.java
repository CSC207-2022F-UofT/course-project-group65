package useCases.assignObserver;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AssignObserverOD {
    String assignee;
    int gameID;
    LinkedHashMap<Integer, String> gameToReferee;

    public AssignObserverOD(String assignee, int gameID, int roundNum, LinkedHashMap<Integer, String> gameToReferee) {
        this.assignee = assignee;
        this.gameID = gameID;
        this.gameToReferee = gameToReferee;
    }

    public LinkedHashMap<Integer, String> getGameToReferee() {
        return gameToReferee;
    }

    public String getAssignee() {
        return assignee;
    }

    public int getGameID() {
        return gameID;
    }
}
