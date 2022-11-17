package useCases.endTourn;


import entities.*;

import java.util.List;

/**
 * The input data class for the start bracket input boundary.
 */
public class EndTournID {
    private BracketRepo brackets;
    private String currentUser;
    private AccountRepo accountRepo;
    private int bracketId;

    public EndTournID(BracketRepo brackets, String currentUser, AccountRepo accountRepo, int bracketId) {
        this.brackets = brackets;
        this.currentUser = currentUser;
        this.accountRepo = accountRepo;
        this.bracketId = bracketId;
    }


}

