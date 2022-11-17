package useCases.endTourn;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;

/**
 * The output data class for the end tournament output boundary.
 */
public class EndTournOD {
    private String username;
    private AccountRepo accounts;
    private BracketRepo brackets;
    private int bracketId;
    public EndTournOD(String username, AccountRepo accounts, BracketRepo brackets, int bracketId) {
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
