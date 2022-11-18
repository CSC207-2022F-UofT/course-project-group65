package useCases.startTourn;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;

import java.util.ArrayList;

/**
 * The output data class for the start tournament output boundary.
 */
public class StartTournOD {
    private String username;
    private AccountRepo accounts;
    private BracketRepo brackets;
    private int bracketId;
    private ArrayList<String> errors;
    public StartTournOD(String username, AccountRepo accounts, BracketRepo brackets,
                        int bracketId, ArrayList<String> errors) {
        this.username = username;
        this.accounts = accounts;
        this.brackets = brackets;
        this.bracketId = bracketId;
        this.errors = errors;
    }

    public ArrayList<String> getErrors() {
        return this.errors;
    }
    public Bracket getBracket() {
        return this.brackets.getBracket(this.bracketId);
    }

    public User getUser() { return this.accounts.getUser(username); }
}
