package use_cases.assign_observer;

public class AssignObserverID {
    private final String assignee;
    private final int gameID;

    public AssignObserverID(String assignee, int gameID){
        this.assignee = assignee;
        this.gameID = gameID;
    }

    String getAssignee() {
        return assignee;
    }

    int getGameID() {
        return gameID;
    }
}
