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

    void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    int getGameID() {
        return gameID;
    }

    void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
