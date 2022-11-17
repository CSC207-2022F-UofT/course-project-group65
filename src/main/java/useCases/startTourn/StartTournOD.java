package useCases.startTourn;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;

/**
 * The output data class for the start tournament output boundary.
 */
public class StartTournOD {
    private String username;
    private AccountRepo accounts;
    private BracketRepo brackets;
    private int bracketId;
    public StartTournOD(String username, AccountRepo accounts, BracketRepo brackets, int bracketId) {
        this.username = username;
        this.accounts = accounts;
        this.brackets = brackets;
        this.bracketId = bracketId;
    }

    public Bracket getBracket() {
        return this.brackets.getBracket(this.bracketId);
    }

    public User getUser() { return this.accounts.getUser(username); }
}
