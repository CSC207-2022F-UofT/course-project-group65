package use_cases.assign_observer;

public class AssignObserverID {
    private final String ASSIGNEE;
    private final int GAME_ID;

    public AssignObserverID(String assignee, int gameID){
        ASSIGNEE = assignee;
        GAME_ID = gameID;
    }

    String getAssignee() {
        return ASSIGNEE;
    }

    int getGameID() {
        return GAME_ID;
    }
}
