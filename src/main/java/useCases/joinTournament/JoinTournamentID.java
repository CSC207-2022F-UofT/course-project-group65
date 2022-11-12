package useCases.joinTournament;

import entities.BracketRepo;
import entities.User;

public class JoinTournamentID {
    private String invite;
    private User currUser;
    private BracketRepo repo;

    public JoinTournamentID(String invite, User currUser){
        this.invite = invite;
        this.currUser = currUser;
    }
    String getInvite() {
        return invite;
    }

    void setInvite(String invite) {
        this.invite = invite;
    }

    User getCurrUser() {
        return currUser;
    }

    void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    public BracketRepo getRepo() {
        return repo;
    }

    public void setRepo(BracketRepo repo) {
        this.repo = repo;
    }
}
