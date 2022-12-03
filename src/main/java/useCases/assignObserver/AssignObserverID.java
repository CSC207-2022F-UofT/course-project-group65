package useCases.assignObserver;

public class AssignObserverID {
    private String assignee;
    private int gameID;

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
