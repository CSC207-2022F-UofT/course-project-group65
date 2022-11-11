package useCases.assignObserver;

import entities.Bracket;
import entities.User;

public class AssignObserverID {
    private User currUser;
    private String assignee;
    private int gameID;
    private Bracket tournament;

    public AssignObserverID(User currUser, String assignee, int gameID, Bracket tournament){
        this.currUser = currUser;
        this.assignee = assignee;
        this.gameID = gameID;
        this.tournament = tournament;
    }

    User getCurrUser() {
        return currUser;
    }

    void setCurrUser(User currUser) {
        this.currUser = currUser;
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

    Bracket getTournament() {
        return tournament;
    }

    void setTournament(Bracket tournament) {
        this.tournament = tournament;
    }
}
