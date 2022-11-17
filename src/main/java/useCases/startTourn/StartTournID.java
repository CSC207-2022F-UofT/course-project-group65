package useCases.startTourn;

import entities.*;

import java.util.List;

public class StartTournID {
    private BracketRepo brackets;
    private String currentUser;
    private AccountRepo accountRepo;
    private int bracketId;

    public StartTournID(BracketRepo brackets, String currentUser, AccountRepo accountRepo, int bracketId) {
        this.brackets = brackets;
        this.currentUser = currentUser;
        this.accountRepo = accountRepo;
        this.bracketId = bracketId;
    }
    public Bracket getBracket() {
        return this.brackets.getBracket(bracketId);
    }

    public User getUser() { return this.accountRepo.getUser(currentUser);}

    public String getUserRole() {
        return getUser().getBracketRole(bracketId);
    }

    public int getMaxNumTeams() {
        return this.getBracket().getTeamSize();
    }

    public Game getFinalGame() {
        return this.getBracket().getFinalGame();
    }

    public List getTeams() {
        return this.getBracket().getTeams();
    }
}
