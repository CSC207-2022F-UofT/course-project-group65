package useCases.viewTournament;

import entities.User;

public class ViewTournamentID {
    private int tournamentID;
    private User currUser;

    public ViewTournamentID(int tournamentID, User currUser){
        this.tournamentID = tournamentID;
        this.currUser = currUser;
    }
    int getTournamentID() {
        return tournamentID;
    }

    void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    User getCurrUser() {
        return currUser;
    }

    void setCurrUser(User currUser) {
        this.currUser = currUser;
    }
}
