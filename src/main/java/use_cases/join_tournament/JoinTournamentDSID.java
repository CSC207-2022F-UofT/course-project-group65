package use_cases.join_tournament;

import entities.AccountRepo;
import entities.BracketRepo;

/**
 * This class is used to save the brackets and accounts following join tournament to a file.
 */
public class JoinTournamentDSID {
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;

    /**
     * Constructor for the ChangePointsDSID class.
     *
     * @param bracketRepo the updated bracket repo
     * @param accountRepo the updated account repo
     */
    public JoinTournamentDSID(BracketRepo bracketRepo, AccountRepo accountRepo){
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

    /**
     * Getter for the updated bracket repo.
     *
     * @return the updated bracket repo
     */
    public BracketRepo getBracketRepo(){
        return this.bracketRepo;
    }

    /**
     * Getter for the updated account repo.
     *
     * @return the updated account repo
     */
    public AccountRepo getAccountRepo(){
        return this.accountRepo;
    }

}
